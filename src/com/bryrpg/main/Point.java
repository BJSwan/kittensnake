package com.bryrpg.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Point extends GameObject{
	
	private Handler handler;
		
	public Point(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 4, 4);
	}
	
	public void tick() {
	}

	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect((int) x,(int)  y, 4, 4);
	}

}
