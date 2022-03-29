package com.bryrpg.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

public class Player extends GameObject{
	LinkedList<Point> path = new LinkedList<Point>();
	
	Random r = new Random();
	Handler handler;
	boolean setInitDir = true;
	boolean runAway = false;
	private long loseCounter = 0;
	private long loseCounter2 = 0;
	private boolean anim = true;
	private int animCounter = 0;
	
	private int dir = 0;
	
	private BufferedImage player_imageup1;
	private BufferedImage player_imageup2;
	private BufferedImage player_imageright1;
	private BufferedImage player_imageright2;
	private BufferedImage player_imagedown1;
	private BufferedImage player_imagedown2;
	private BufferedImage player_imageleft1;
	private BufferedImage player_imageleft2;
	
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(Game.sarahSheet);
		
		player_imagedown1 = ss.grabImage(1, 1, 16, 16);
		player_imagedown2 = ss.grabImage(2, 1, 16, 16);
		player_imageleft1 = ss.grabImage(1, 2, 16, 16);
		player_imageleft2 = ss.grabImage(2, 2, 16, 16);
		player_imageup1 = ss.grabImage(1, 3, 16, 16);
		player_imageup2 = ss.grabImage(2, 3, 16, 16);
		player_imageright1 = ss.grabImage(1, 4, 16, 16);
		player_imageright2 = ss.grabImage(2, 4, 16, 16);
	}
	
	
	public Rectangle getBounds() {
		return new Rectangle((int)x + 3, (int)y + 1, 9, 15);
	}
	
	public void tick() {
		
		animCounter++;
		
		if(animCounter == 5) {
			anim = !anim;
			animCounter = 0;
		}
		
		if (setInitDir == true){
			velY = -5;
			handler.addPath(path);
			setInitDir = false;
			loseCounter = 0;
			loseCounter2 = 0;
		}
		
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 20);
		y = Game.clamp(y, 0, Game.HEIGHT - 45);
		
		if(velY > 0) {
			dir = 0;
		}
		else if(velX < 0) {
			dir = 1;
		}
		else if(velY < 0) {
			dir = 2;
		}
		else if(velX > 0) {
			dir = 3;
		}
		
		
		
		if(handler.tails.size() > 0) {
				Point point = new Point(this.getX(), this.getY(), ID.Point, handler);
				path.add(point);
		}
		collision();
		if(HUD.RUNAWAY == true) {
			this.velX = 0;
			this.velY = 0;
		}
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Kitten) {
				if(getBounds().intersects(tempObject.getBounds())) {
					for(int j = 0; j < handler.kittens.size(); j++) {
						Kitten tempKitten = handler.kittens.get(j);
						if(getBounds().intersects(tempKitten.getBounds())) {
							handler.removeObject(tempObject);
							handler.removeKitten(tempKitten);
							Kitten kitten = new Kitten(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Kitten, handler, new SpriteSheet(Game.kittenSheet));
							if(handler.kittens.size() < 5) {
								handler.addObject(kitten);
								System.out.println("Player Kitten");
								handler.addKitten(kitten);
							}
							Point point = new Point(this.getX() + -4*(this.velX), this.getY() + -4*(this.velY), ID.Point, handler);
							path.add(point);
							Tail tail = new Tail(this.getX() - 2000, this.getY() -2000, ID.Tail, handler.tails.size() + 1, handler, tempKitten.getRow(), tempKitten.getCol(), new SpriteSheet(Game.kittenSheet));
							handler.addObject(tail);
							handler.addTail(tail);
						}
					}
				}
			}
			else if(tempObject.getID() == ID.Tail) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.RUNAWAY = true;
				}
			}
		}
		if(this.getX() == Game.WIDTH -20 || this.getY() == Game.HEIGHT - 45 || this.getX() == 0 || this.getY() == 0 ) {
			HUD.RUNAWAY = true;
		}
		if(HUD.RUNAWAY) {
			loseCounter++;
			if(loseCounter > 100) {
				loseCounter2++;
				if(loseCounter2 > 100) {
					HUD.LOSE = true;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if(dir == 0) {
			if(anim) {
				g.drawImage(player_imagedown1, (int)x, (int)y, null);
			}
			else if(!anim) {
				g.drawImage(player_imagedown2, (int)x, (int)y, null);
			}
		}
		else if(dir == 1) {
			if(anim) {
				g.drawImage(player_imageleft1, (int)x, (int)y, null);
			}
			else if(!anim) {
				g.drawImage(player_imageleft2, (int)x, (int)y, null);
			}
		}
		else if(dir == 2) {
			if(anim) {
				g.drawImage(player_imageup1, (int)x, (int)y, null);
			}
			else if(!anim) {
				g.drawImage(player_imageup2, (int)x, (int)y, null);
			}
		}
		else if(dir == 3) {
			if(anim) {
				g.drawImage(player_imageright1, (int)x, (int)y, null);
			}
			else if(!anim) {
				g.drawImage(player_imageright2, (int)x, (int)y, null);
			}
		}
	}
	
	
	
}