package com.dss_game;

import com.example.dss_game.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Shield implements Weapon {
	Bitmap image;
	int readiness =0;

	public Shield (Engine engine) {
		image = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(engine.getResources(), R.drawable.shield), 200, 500, false);

	}
	@Override
	public Bitmap image() {
		return image;
	}

	@Override
	public int readiness() {
		return (readiness > 0) ? readiness-- : readiness;
	}
	@Override
	public Bitmap menu(Paint p) {
		Bitmap b = Bitmap.createBitmap(300, 400, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(b);
		c.drawText("Fucker", 10, 150, p);
		return b;
	}
	@Override
	public boolean tapped(int x, int y) {
		System.out.println("Checking");
		readiness = 100;

		return true;
	}

}
