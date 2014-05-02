package com.dss_game.dungeon;

import java.util.LinkedList;

import android.graphics.Rect;

import com.dss_game.Action;
import com.dss_game.Demon;
import com.dss_game.GameMenu;
import com.dss_game.Monster;
import com.dss_game.Engine;
import com.dss_game.Slime;
import com.dss_game.Spider;

public class Room {

	public Monster monster;
	//public Treasure[] treasures;
	//TODO: implement treasure
	public int x, y, w, h;
	public Rect rec, drawrec, colrec;
	public boolean visited = false;
	public LinkedList<Room> sentinel ;
	public LinkedList<Room> links;
	GameMenu menu;
	public Room() {
		x = rand(50);
		y = rand(50);
		w = rand(5) + 3;
		h = rand(5) + 3;
		rec = new Rect(x, y, x + w, y + h);
		drawrec = new Rect((int)(x * 50 * com.dss_game.Engine.scaleX), (int)(y * 50 * com.dss_game.Engine.scaleY), (int)((x * 50 + w * 50) * com.dss_game.Engine.scaleX), (int)((y * 50 + h * 50) * com.dss_game.Engine.scaleY));
		sentinel = new LinkedList<Room>();
		links = new LinkedList<Room>();
		sentinel.add(this);
		menu = new GameMenu();
		menu.addOption("Loot", new LootRoom());
		switch((int)(Math.random() * 5)){
		case 0:
			monster = new Slime();
			break;
		case 1:
			monster = new Spider();
			break;
		default:
			monster = new Demon();
		}
	}

	int rand(int x) {
		return (int)(Math.random() * x);
	}

	public GameMenu getMenu() {
		return menu;
	}

	private class LootRoom implements Action {



		@Override
		public int execute() {
			// TODO Auto-generated method stub
			if (Engine.player != null) {
				Engine.player.give(0);
				System.out.println("Loot");
			}
			menu = new GameMenu();
			return 0;
		}
	}

}
