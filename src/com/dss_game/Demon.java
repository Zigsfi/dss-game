package com.dss_game;

import com.example.dss_game.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Demon implements Monster {
	Engine engine;
	Bitmap image;
	int x, y;
	public Demon() {
		x = 900;
		y = 500;
	}

	@Override
	public void init(Engine e) {
		this.engine = e;
		image = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.monster),(int)( 240 * Engine.scaleX), (int)(320 * Engine.scaleY), false);
	}

	@Override
	public void draw(Canvas c) {
		c.drawBitmap(image, (int) (Engine.scaleX * x), (int)(Engine.scaleY * y), engine.paint);
	}

	@Override
	public void update() {

	}

}
