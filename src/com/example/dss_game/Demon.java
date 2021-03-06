package com.example.dss_game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Demon implements Monster {
	Engine engine;
	Bitmap image;
	int x, y;
	public Demon() {
		x = 500;
		y = 200;
	}

	@Override
	public void init(Engine e) {
		this.engine = e;
		image = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.monster), 240, 320, false);
	}

	@Override
	public void draw(Canvas c) {
		c.drawBitmap(image, x, y, engine.paint);
	}

	@Override
	public void update() {

	}

}
