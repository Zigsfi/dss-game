package com.dss_game.dungeon;

import java.util.LinkedList;

import android.graphics.Rect;

import com.dss_game.Monster;

public class Room {
	
	public Monster[] monsters;
	//public Treasure[] treasures;
	//TODO: implement treasure
	public int x, y, w, h;
	public Rect rec, drawrec;
	public boolean visited = false;
	public LinkedList<Room> sentinel ;
	public LinkedList<Room> links;
	public Room() {
		x = rand(30);
		y = rand(30);
		w = rand(5 ) + 1;
		h = rand(5 ) + 1;
		rec = new Rect(x, y, x + w, y + h);
		drawrec = new Rect(x * 30, y * 30, x * 30 + w * 30, y * 30 + h * 30);
		sentinel = new LinkedList<Room>();
		links = new LinkedList<Room>();
		sentinel.add(this);
	//	rec = new Rect(x * 30, y * 30, x * 30 + w * 30, y * 30 + h * 30);
	}
	
	int rand(int x) {
		return (int)(Math.random() * x);
	}
	
}
