package com.example.dss_game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Shield implements Weapon {
	Bitmap image;

	public Shield (Engine engine) {
		image = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(engine.getResources(), R.drawable.shield), 200, 500, false);

	}
	@Override
	public Bitmap image() {
		// TODO Auto-generated method stub
		return image;
	}

	@Override
	public int readiness() {
		// TODO Auto-generated method stub
		return 0;
	}

}
