package com.bryrpg.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 6570485106852300062L;

	public static final int WIDTH = 640, HEIGHT = WIDTH/12*9;
	
	private Thread thread;
	
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Menu menu;
	
	public enum STATE{
		Menu,
		Help,
		End,
		Music,
		Game;
	}
	
	public static STATE gameState = STATE.Menu;
	
	private BufferedImage catSprite;
	public static BufferedImage kittenSheet;
	public static BufferedImage sarahSheet;
	
	public Game() {
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImageLoader loader2 = new BufferedImageLoader();
		BufferedImageLoader loader3 = new BufferedImageLoader();
		catSprite = loader.loadImage("/musicbutton.png");
		kittenSheet = loader2.loadImage("/kittensprites.png");
		sarahSheet = loader3.loadImage("/sarahsprites.png");
		
		handler = new Handler();
		Random r = new Random();
		hud = new HUD(handler);
		
		menu = new Menu(this, handler, hud, new SpriteSheet(catSprite));
		
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		
		AudioPlayer.load();
		
		AudioPlayer.getMusic("music1").loop();
		
		new Window(WIDTH, HEIGHT, "Kitten Snake", this);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer+=1000;
				System.out.println("FPS: " + frames);
				System.out.println("Objects: " + handler.object.size());
				System.out.println("Tail: " + handler.tails.size());
				System.out.println("Current Kittens: " + handler.kittens.size());
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		if(gameState == STATE.Game){
			hud.tick();
			handler.tick();
			if (hud.LOSE == true) {
				gameState = STATE.End;
				handler.clearObjects();
				HUD.LOSE = false;
				HUD.RUNAWAY = false;
			}
		}
		else if (gameState == STATE.Menu){
			menu.tick();
			handler.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.pink);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(gameState == STATE.Game) {
			hud.render(g);
		}
		else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Music || gameState == STATE.End){
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max) {
			return var = max;
		}
		else if(var <= min){
			return var = min;
		}
		else {
			return var;
		}
	}
	
	public static void main(String[] args) {
		System.setProperty("org.lwjgl.librarypath", new File("slick").getAbsolutePath());
		new Game();
	}
}
