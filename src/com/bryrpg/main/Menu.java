package com.bryrpg.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.bryrpg.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	private SpriteSheet ss;
	private BufferedImage musicButton;
	
	public Menu(Game game, Handler handler, HUD hud, SpriteSheet ss) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.ss = ss;
		
		musicButton = ss.grabImage(1, 1, 64, 64);
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//Menu
		if(mouseOver(mx, my, 225, 100, 200, 64) && game.gameState == STATE.Menu){
			game.gameState = STATE.Game;
			handler.addObject(new Player(game.WIDTH/2-32, game.HEIGHT-50, ID.Player, handler));
			for(int i = 0; i<5; i++) {
				Kitten kitten = new Kitten(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Kitten, handler, new SpriteSheet(Game.kittenSheet));
				handler.addObject(kitten);
				System.out.println("Menu Kitten");
				handler.addKitten(kitten);
			}
			AudioPlayer.getSound("menu_sound").play();
			return;
		}
		else if(mouseOver(mx, my, 225, 200, 200, 64) && game.gameState == STATE.Menu){
			game.gameState = STATE.Help;
			AudioPlayer.getSound("menu_sound").play();
		}
		else if(mouseOver(mx, my, 80, 200, 64, 64) && game.gameState == STATE.Menu) {
			game.gameState = STATE.Music;
		}
		else if(mouseOver(mx, my, 225, 300, 200, 64) && game.gameState == STATE.Menu){
			System.exit(1);
		}
		
		//Help
		else if(mouseOver(mx, my, 225, 300, 200, 64) && (game.gameState == STATE.Help)){
			game.gameState = STATE.Menu;
			AudioPlayer.getSound("menu_sound").play();
		}
		
		//Music
		else if(mouseOver(mx, my, 225, 100, 200, 64) && (game.gameState == STATE.Music)){
			AudioPlayer.getMusic("music1").loop();
			AudioPlayer.getSound("menu_sound").play();
		}
		else if(mouseOver(mx, my, 225, 200, 200, 64) && game.gameState == STATE.Music){
			AudioPlayer.getMusic2("music2").loop();
			AudioPlayer.getMusic2("music2").setVolume((float)0.5);
			AudioPlayer.getSound("menu_sound").play();
		}
		else if(mouseOver(mx, my, 225, 300, 200, 64) && (game.gameState == STATE.Music)){
			game.gameState = STATE.Menu;
			AudioPlayer.getSound("menu_sound").play();
		}
		
		//Game Over Man...
		else if(mouseOver(mx, my, 225, 300, 200, 64) && (game.gameState == STATE.End)){
			game.gameState = STATE.Menu;
			AudioPlayer.getSound("menu_sound").play();
			hud.score(0);
			for(int i = 0; i < handler.kittens.size(); i++) {
				Kitten tempKitten = handler.kittens.get(i);
				handler.removeKitten(tempKitten);
				System.out.println("Kitten Removed");
			}
			for(int i = 0; i < handler.kittens.size(); i++) {
				Kitten tempKitten = handler.kittens.get(i);
				handler.removeKitten(tempKitten);
				System.out.println("Kitten Removed");
			}
			for(int i = 0; i < handler.kittens.size(); i++) {
				Kitten tempKitten = handler.kittens.get(i);
				handler.removeKitten(tempKitten);
				System.out.println("Kitten Removed");
			}
			for(int j = 0; j < handler.object.size(); j++) {
				GameObject tempObject = handler.object.get(j);
				handler.removeObject(tempObject);
			}
		}
	}
	
	public void MouseRelease(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		
		if(game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			g.setFont(fnt);
			g.drawString("Kitten Snake!", 170, 70);
			
			g.setFont(fnt2);
			g.drawString("Play", 290, 145);
			
			g.drawString("Help", 290, 245);
			
			g.drawString("Quit", 290, 345);
			
			g.drawRect(225, 100, 200, 64);
			
			g.drawRect(225, 200, 200, 64);
			
			g.drawRect(225, 300, 200, 64);
			
			g.drawString("Music", 70, 190);
			
			g.drawImage(musicButton, 80, 200, null);
		}
		else if(game.gameState == STATE.Music) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 25);
			g.setFont(fnt);
			g.drawString("Select Music", 170, 70);
			
			g.setFont(fnt2);
			g.drawString("Simple Livin'", 250, 145);
			
			g.drawString("America Feline", 230, 245);
			
			g.drawString("Back", 290, 345);
			
			g.drawRect(225, 100, 200, 64);
			
			g.drawRect(225, 200, 200, 64);
			
			g.drawRect(225, 300, 200, 64);
			
		}
		else if(game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			g.setFont(fnt);
			g.drawString("Help", 260, 70);
			Font fnt3 = new Font("arial", 1, 11);
			g.setFont(fnt3);
			g.drawString(" ", 260, 70);
			g.drawString("Sarah wants to have some kittens.", 230, 120);
			g.drawString("Help her collect them.", 230, 130);
			g.drawString("Move with the WASD keys.", 230, 140);
			g.drawString("Don't run into the wall or your kitten trail", 230, 150);
			g.drawString("or your kittens will run away.", 230, 160);
			g.drawString("Good Luck!", 230, 170);
			g.setFont(fnt2);
			g.drawString("Back", 290, 345);
			g.drawRect(225, 300, 200, 64);
		}
		else if(game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			g.setFont(fnt);
			g.drawString("Game Over!", 170, 70);
			Font fnt3 = new Font("arial", 1, 11);
			g.setFont(fnt3);
			g.drawString(" ", 260, 70);
			g.drawString("Bummer!", 230, 120);
			g.drawString("Kittens Collected: " + (int) hud.getScore(), 230, 150);
			g.setFont(fnt2);
			g.drawString("Try Again?", 250, 345);
			g.drawRect(225, 300, 200, 64);
		}
	}
	
}
