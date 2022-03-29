package com.bryrpg.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	private float score = 0;
	public static boolean LOSE = false;
	public static boolean RUNAWAY = false;

	private Handler handler;
	
	HUD(Handler handler){
		this.handler = handler;
	}
	
	public void tick() {
		score = handler.tails.size();
	}
	
	public void render(Graphics g) {	
		g.setColor(Color.white);
		g.drawString("Kittens: " + (int) score, 15, 35);
	}
	
	public void score(float score) {
		this.score = score;
	}
	
	public float getScore() {
		return score;
	}
}
