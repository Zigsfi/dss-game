package com.example.dss_game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
		return 0;
	}

}
