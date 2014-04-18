package com.dss_game;

import com.example.dss_game.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

public class Sword implements Weapon {
	Bitmap weapon;
	int readiness;
	public Sword(Engine e)  {
		weapon = Bitmap.createScaledBitmap( 
				BitmapFactory.decodeResource(e.getResources(), R.drawable.sword),
				(int)(400 * Engine.scaleX),(int)( 800 * Engine.scaleY), false);

	}
	@Override
	public Bitmap image() {
		// TODO Auto-generated method stub
		return weapon;
	}

	@Override
	public int readiness() {
		// TODO Auto-generated method stub
		return (readiness > 0) ? readiness-- : readiness;
	}

	@Override
	public boolean tapped(int x, int y) {
		System.out.println("Sword ");
		return false;
	}
	@Override
	public Bitmap menu(int x, int y, Paint p) {
		// TODO Auto-generated method stub
		return null;
	}

}
