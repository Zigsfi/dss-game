package com.dss_game;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class GameMenu {
	ArrayList <MenuItem> menu;
	private int recentlyClicked;
	public GameMenu() {
		menu = new ArrayList<MenuItem>();
	}
	
	public void addOption(String s, Action a) {
		MenuItem m = new MenuItem(s, a);
		menu.add(m);
	}
	
	public Bitmap render(int sizeX, int sizeY, Paint p) {
	//	Paint p = new Paint();
		Bitmap b = Bitmap.createBitmap((int)(sizeX * Engine.scaleX), (int)(sizeY * Engine.scaleY), Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(b);
		Paint transparent = new Paint();
		transparent.setARGB(128, 128, 128, 128);
		c.drawRect(0, 0, b.getWidth(), b.getHeight(), transparent);
		p.setTextSize(70 * Engine.scaleY);
		for (int i = 0; i < menu.size(); i++) {
			if (i == recentlyClicked)
				p.setARGB(255, 255, 255, 0);
			else
				p.setARGB(255, 0, 0, 0);
			c.drawText(menu.get(i).getName(), 10, (i * 80 + 50) * Engine.scaleY, p);
			//System.out.println(menu.get(i).getName());
		}
		return b;
	}
	
	public Action click(int x, int y) {
		if (y < 0) return null;
		int i = (int)( (y / (80 * Engine.scaleY)));
		System.out.println(i);

		if (i < menu.size() && i >= 0) {
			recentlyClicked = i;

			return menu.get(i).getAction();
		}
		return null;
	}
	
	private class MenuItem {
		String name;
		Action action;
		
		public MenuItem(String s, Action a) {
			name = s;
			action = a;
		}
		
		public String getName() {
			return name;
		}
		
		public Action getAction() {
			return action;
		}
		public void exec() {
			action.execute();
		}
	}
}

