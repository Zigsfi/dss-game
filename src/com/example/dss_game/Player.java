package com.example.dss_game;

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
		c.drawBitmap(right.image(), 750, 300+right.readiness(), engine.paint);
		c.drawBitmap(left.image(), 200, 340+left.readiness(), engine.paint);
	}
}
