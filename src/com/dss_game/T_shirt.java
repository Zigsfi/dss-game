package com.dss_game;

import java.util.Timer;
import java.util.TimerTask;




import android.graphics.Bitmap;
import android.graphics.Paint;

public class T_shirt implements Armor{
	int readiness=0;
	GameMenu menu;
	//TODO give this T-shirt states
	int defence = 5;
	
	private int menuX;
	private int menuY;
	Engine engine;	
	
	public T_shirt (Engine e){
		engine = e;
		menu = new GameMenu();
		menu.addOption("Hulk Rip", new HulkRip());
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
		Action a = menu.click(x - menuX, y - menuY);
		
		System.out.println("Checking");
		if (readiness <= 0) {
			readiness = 100;
			if (a != null)
				a.execute();
			else 
				System.out.println("No action");

			return true;
		}
		System.out.println("Not ready yet");
		return false;
	}
	
	private class HulkRip implements Action {
		@Override
		public void execute() {
			// TODO polish up and see if this how we want to do this
			engine.player.changeDef( defence);
			Timer def_T = new Timer();
			def_T.schedule(new TimerTask() {
				@Override
				public void run() {
					engine.player.changeDef((-1 * defence));
					
					return;
				}
			}, 1000*3);
			
			
		}
	}

}
