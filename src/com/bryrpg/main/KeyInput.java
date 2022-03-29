package com.bryrpg.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	private GameObject player;
	
	Game game;
	
	public KeyInput(Handler handler, Game game){
		this.handler = handler;
		
		this.game = game;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i< handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player && HUD.RUNAWAY == false) {
				
				if(key == KeyEvent.VK_W) {
					if(tempObject.getVelY() == 0){
						tempObject.setVelY(-5);
						tempObject.setVelX(0);
					}
					keyDown[0] = true;
				}
				if(key == KeyEvent.VK_A) {
					if(tempObject.getVelX() == 0){
						tempObject.setVelX(-5);
						tempObject.setVelY(0);
					}
					keyDown[1] = true;
				}
				if(key == KeyEvent.VK_S) {
					if(tempObject.getVelY() == 0){
						tempObject.setVelY(5);
						tempObject.setVelX(0);
					}
					keyDown[2] = true;
				}
				if(key == KeyEvent.VK_D) {
					if(tempObject.getVelX() == 0){
						tempObject.setVelX(5);
						tempObject.setVelY(0);
					}
					keyDown[3] = true;
				}
				
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i< handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {
				
				if(key == KeyEvent.VK_W) {
					keyDown[0] = false;
				}
				if(key == KeyEvent.VK_A) {
					keyDown[1] = false;
				}
				if(key == KeyEvent.VK_S) {
					keyDown[2] = false;
				}
				if(key == KeyEvent.VK_D) {
					keyDown[3] = false;
				}
				
			}
		}
	}
}
