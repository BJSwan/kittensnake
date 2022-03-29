package com.bryrpg.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

public class Tail extends GameObject{
	
	LinkedList<Point> path = new LinkedList<Point>();
	
	private Handler handler;
	private int count;
	private int timer;
	private boolean addPath = false;
	private boolean runAway = false;
	private SpriteSheet ss;
	Random r = new Random();
	
	private BufferedImage tail_image;
	
	public Tail(float x, float y, ID id, int count, Handler handler, int row, int col, SpriteSheet ss) {
		super(x, y, id);
		
		this.handler = handler;
		this.count = count;
		
		this.ss = ss;
		tail_image = ss.grabImage(col, row, 16, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x + 4, (int) y + 4, 8, 8);
	}
	
	public void tick() {
		if(HUD.RUNAWAY == true && runAway == false) {
			runAway = true;
		}
		if(runAway == true) {
			this.velX = (r.nextInt(5 - (-5)) + -5);
			this.velY = (r.nextInt(5 - (-5)) + -5);
			if(velX == 0) {
				velX = 5;
			}
			x += velX;
			y += velY;
		}
		else if(runAway == false) {
			if(!addPath) {
				handler.addPath(path);
				addPath = true;
			}
			
				LinkedList<Point> tempPath = handler.paths.get(this.count - 1);
				float lastX = tempPath.getFirst().getX();
				float lastY = tempPath.getFirst().getY();
				
				if(handler.tails.size() > this.count) {
					Point point = new Point(this.getX(), this.getY(), ID.Point, handler);
					path.add(point);
				}
				
				if(this.count == 1) {
					for(int i = 0; i < handler.object.size(); i++) {
						
						GameObject tempObject = handler.object.get(i);
						int xOffset = 0;
						int yOffset = 0;
						if(tempObject.getID() == ID.Player) {
							if(tempObject.getVelX()<0) {
								xOffset = 20;
								yOffset = 0;
							}
							if(tempObject.getVelX()>0) {
								xOffset = -20;
								yOffset = 0;
							}
							if(tempObject.getVelY()<0) {
								xOffset = 0;
								yOffset = 20;
							}
							if(tempObject.getVelY()>0) {
								xOffset = 0;
								yOffset = -20;
							}
							this.setX(tempObject.getX()+xOffset);
							this.setY(tempObject.getY()+yOffset);
						}
					}
				}
				else {
					this.setX(lastX);
					this.setY(lastY);
				}
				tempPath.remove(0);
			
			collision();
		}
	}

	public void render(Graphics g) {
		
		g.drawImage(tail_image, (int)x, (int)y, null);
	}
	
	private void collision() {
	}
}
