package com.dss_game;

import com.example.dss_game.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Canvas;

public class BattleAxe implements Weapon {
	Bitmap image;
	int readiness=0;
	GameMenu menu;
	//TODO give this sword states
	int attack = 20;
	
	private int menuX;
	private int menuY;
	Engine engine;	
	
	public BattleAxe(Engine e)  {
		engine = e;
		image = Bitmap.createScaledBitmap( 
				BitmapFactory.decodeResource(engine.getResources(), R.drawable.battleaxe),
				(int)(400 * Engine.scaleX),(int)( 800 * Engine.scaleY), false);
		menu = new GameMenu();
		menu.addOption("Chop", new Chop());
		menu.addOption("Cleave", new Cleave());

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
		
	private class Cleave implements Action {

		@Override
		public int execute() {
			// TODO select an enemy from monster array in engine
			int damage = -1 * (engine.player.getStr() + attack);
			engine.monster.take_dmg(damage);	
			Engine.message = "Cleave did " + -(damage + ((Stats)(engine.monster)).getDef()) + " damage.";

			return 300;
		}
		
	}
	public String name() {
		return "Battle Axe";
	}
	private class Chop implements Action {

		@Override
		public int execute() {
			// TODO select an enemy from monster array in engine
			int damage =  (int)(-0.5 * (engine.player.getStr() + attack));
			engine.monster.take_dmg(damage);
			Engine.message = "Chop did " + -(damage + ((Stats)(engine.monster)).getDef()) + " damage.";
			return 200;
			
		}}

	@Override
	public void setReadiness(int i) {
		// TODO Auto-generated method stub
		readiness = i;
	}
	
	

}
