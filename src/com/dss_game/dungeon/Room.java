package com.dss_game.dungeon;

import java.util.LinkedList;

import android.graphics.Rect;

import com.dss_game.GameMenu;
import com.dss_game.Monster;
import com.example.dss_game.Engine;

public class Room {
	
	public Monster[] monsters;
	//public Treasure[] treasures;
	//TODO: implement treasure
	public int x, y, w, h;
	public Rect rec, drawrec, colrec;
	public boolean visited = false;
	public LinkedList<Room> sentinel ;
	public LinkedList<Room> links;
	private GameMenu menu;
	public Room() {
		x = rand(50);
		y = rand(50);
		w = rand(5) + 1;
		h = rand(5) + 1;
		rec = new Rect(x, y, x + w, y + h);
		drawrec = new Rect((int)(x * 50 * com.dss_game.Engine.scaleX), (int)(y * 50 * com.dss_game.Engine.scaleY), (int)((x * 50 + w * 50) * com.dss_game.Engine.scaleX), (int)((y * 50 + h * 50) * com.dss_game.Engine.scaleY));
		sentinel = new LinkedList<Room>();
		links = new LinkedList<Room>();
		sentinel.add(this);
		menu = new GameMenu();
		menu.addOption("Balls", null);
	}
	
	int rand(int x) {
		return (int)(Math.random() * x);
	}
	
	public GameMenu getMenu() {
		return menu;
	}
	
}
