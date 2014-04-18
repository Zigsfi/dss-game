package com.dss_game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Player {
	Engine engine;
	Weapon left, right;
	public Player(Engine e) {
		engine = e;
		right = new Sword(e);
		left = new Shield(e);
	}

	public void update() {
	}

	public void draw (Canvas c) {
		c.drawBitmap(right.image(), (int)(1050 * Engine.scaleX), (int)((800+right.readiness()) * Engine.scaleY), engine.paint);
		c.drawBitmap(left.image(), (int)(400 * Engine.scaleX), (int)((600+left.readiness()) * Engine.scaleY), engine.paint);
		c.drawBitmap(left.menu(engine.paint), (int)(200 * Engine.scaleX), (int)((340+left.readiness()) * Engine.scaleY), engine.paint);
	}

	public void handleInput(int x, int y) {
		System.out.println("Handling");
		if (x < 600) {
			left. tapped(x, y);
		}
		if (x > 600) {
			right.tapped(x, y);
		}

	}
}
