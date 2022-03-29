package com.bryrpg.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;



public class AudioPlayer {
	
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	public static Map<String, Music> musicMap2 = new HashMap<String, Music>();

	public static void load() {
		
		try {
			Music catSong = new Music("res/catsong.wav");
			soundMap.put("menu_sound", new Sound("res/clicksound.ogg"));
			musicMap.put("music1", new Music("res/bgmusic.wav"));
			musicMap2.put("music2", catSong);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	public static Music getMusic2(String key) {
		return musicMap2.get(key);
	}
	
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
}
