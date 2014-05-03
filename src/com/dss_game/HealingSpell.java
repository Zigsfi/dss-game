package com.dss_game;

import com.example.dss_game.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Canvas;

public class HealingSpell implements Weapon {
	Bitmap image;
	int readiness=0;
	GameMenu menu;
	//TODO give this sword states
	int attack = 20;
	
	private int menuX;
	private int menuY;
	Engine engine;	
	
	public HealingSpell(Engine e)  {
		engine = e;
		image = Bitmap.createScaledBitmap( 
				BitmapFactory.decodeResource(engine.getResources(), R.drawable.healingspell),
				(int)(400 * Engine.scaleX),(int)( 800 * Engine.scaleY), false);
		menu = new GameMenu();
		menu.addOption("Heal", new Heal());

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
		

	public String name() {
		return "Healing Spell";
	}
	private class Heal implements Action {

		@Override
		public int execute() {
			// TODO select an enemy from monster array in engine
			int damage =  10;
			Engine.player.changeHp(damage);
			Engine.message = "Healed "+ damage + " points of damage.";
			return 200;
			
		}}

	@Override
	public void setReadiness(int i) {
		// TODO Auto-generated method stub
		readiness = i;
	}
	
	

}
