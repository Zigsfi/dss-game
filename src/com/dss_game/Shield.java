package com.dss_game;

import com.example.dss_game.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Shield implements Weapon {
	Bitmap image;
	int readiness =0;
	GameMenu menu;
	private int menuX;
	private int menuY;
	public Shield (Engine engine) {
		image = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(engine.getResources(), R.drawable.shield), (int) (400 * Engine.scaleX),(int)(900 * Engine.scaleY), false);
		menu = new GameMenu();
		menu.addOption("Fucker", new Test());
		menu.addOption("Bitch", null);
	}
	@Override
	public Bitmap image() {
		return image;
	}

	@Override
	public int readiness() {
		return (readiness >= 0) ? readiness-- : readiness;
	}
	@Override
	public Bitmap menu(int x, int y, Paint p) {
		menuX = x;
		menuY = y;
		return menu.render(p);
	}
	
	
	@Override
	public boolean tapped(int x, int y) {
		Action a = menu.click(x - menuX, y - menuY);
		if (a != null)
			a.execute();
		else 
			System.out.println("No action");
		System.out.println("Checking");
		if (readiness <= 0) {
			readiness = 100;

			return true;
		}
		return false;
	}

}
