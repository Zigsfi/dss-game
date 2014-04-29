package com.dss_game;

import com.example.dss_game.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Canvas;

public class Sword implements Weapon {
	Bitmap image;
	int readiness=0;
	GameMenu menu;
	//TODO give this sword states
	int attack = 10;
	
	private int menuX;
	private int menuY;
	Engine engine;	
	
	public Sword(Engine e)  {
		engine = e;
		image = Bitmap.createScaledBitmap( 
				BitmapFactory.decodeResource(engine.getResources(), R.drawable.sword),
				(int)(400 * Engine.scaleX),(int)( 800 * Engine.scaleY), false);
		menu = new GameMenu();
		menu.addOption("Slash", new Slash());
		menu.addOption("Stab", new Stab());

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
		return menu.render(x, y, p);
	}
    
	@Override
	public boolean tapped(int x, int y) {
		
		System.out.println("Checking");
		if (readiness <= 0) {
			Action a = menu.click(x - menuX, y - menuY);

			if (a != null)
				readiness = a.execute();
			else 
				System.out.println("No action");

			return true;
		}
		System.out.println("Not ready yet");
		return false;
	}
		
	private class Stab implements Action {

		@Override
		public int execute() {
			// TODO select an enemy from monster array in engine
			engine.monster.take_dmg( (-1 * (engine.player.getStr() + attack)));			
			return 150;
		}
		
	}
	
	private class Slash implements Action {

		@Override
		public int execute() {
			// TODO select an enemy from monster array in engine
			engine.monster.take_dmg( (int)(-0.5 * (engine.player.getStr() + attack)));
			return 100;
			
		}}
	
	

}
