package com.bryrpg.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	LinkedList<Tail> tails = new LinkedList<Tail>();
	LinkedList<Kitten> kittens = new LinkedList<Kitten>();
	LinkedList<LinkedList<Point>> paths = new LinkedList<LinkedList<Point>>();
	
	
	public void tick() {
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void addTail(Tail tail) {
		this.tails.add(tail);
	}
	
	public void removeTail(Tail tail) {
		this.object.remove(tail);
	}
	
	public void addPath(LinkedList<Point> path) {
		this.paths.add(path);
	}
	
	public void removePath(LinkedList<Point> path) {
		this.paths.remove(path);
	}
	
	public void addKitten(Kitten kitten) {
		this.kittens.add(kitten);
	}
	
	public void removeKitten(Kitten kitten) {
		this.kittens.remove(kitten);
	}
	
	public void clearObjects() {
		object.clear();
		tails.clear();
		paths.clear();
	}
}
