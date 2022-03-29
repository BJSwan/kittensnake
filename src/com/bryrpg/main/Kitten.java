package com.bryrpg.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Kitten extends GameObject{
	
	private Handler handler;
	
	private BufferedImage kitten_image;
	
	private SpriteSheet ss;
	
	Random r = new Random();
	int row = r.nextInt(3) + 1;
	int col = r.nextInt(3) + 1;
	
	public Kitten(float x, float y, ID id, Handler handler, SpriteSheet ss) {
		super(x, y, id);
		
		this.handler = handler;
		
		this.ss = ss;
		
		kitten_image = ss.grabImage(col, row, 16, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}
	
	public void tick() {
	}
	
	public void render(Graphics g) {
		g.drawImage(kitten_image, (int)x, (int)y, null);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
}
