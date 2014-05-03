package com.dss_game;

import java.util.Timer;
import java.util.TimerTask;





import android.graphics.Bitmap;
import android.graphics.Paint;

public class PlateArmor implements Armor{
	int readiness=0;
	GameMenu menu;
	//TODO give this T-shirt states
	int defence = 5;
	
	
	private int menuX;
	private int menuY;
	Engine engine;	
	
	public PlateArmor (Engine e){
		engine = e;
		menu = new GameMenu();
		menu.addOption("Steel Yourself", new HulkRip());
		//menu.addOption("Stab", new Stab());
	}

	@Override
	public int readiness() {
		return (readiness >= 0) ? readiness-- : readiness;
	}

	@Override
	public Bitmap menu(int x, int y, Paint p) {
		menuX = x;
		menuY = y;
		return menu.render(500, 400, p);
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
	
	private class HulkRip implements Action {
		@Override
		public int execute() {
			// TODO polish up and see if this how we want to do this
			engine.player.changeDef( 3);
			Engine.message = "Steeled Yourself: Defense up " + 3;
			Timer def_T = new Timer();
			def_T.schedule(new TimerTask() {
				@Override
				public void run() {
					engine.player.changeDef((-3));
					Engine.message = "Defense down " + 3;
					
					return;
				}
			}, 1000*3);
			
			return 200;
		}
	}
	
	public int defense() {
		return 3;
	}
	public String name() {
		return "Plate Armor";
	}

	@Override
	public void setReadiness(int i) {
		// TODO Auto-generated method stub
		readiness = i;
	}

}
