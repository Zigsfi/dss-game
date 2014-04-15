package com.dss_game;

import com.example.dss_game.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

public class Sword implements Weapon {
	Bitmap weapon;
	int readiness;
	public Sword(Engine e)  {
		weapon = BitmapFactory.decodeResource(e.getResources(), R.drawable.sword);

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
		System.out.println("Sword");
		return false;
	}
	@Override
	public Bitmap menu(Paint p) {
		// TODO Auto-generated method stub
		return null;
	}

}
