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
			engine.player.changeStr( 3);
			Engine.message = "Ripped shirt: Strength up " + 3;
			Timer def_T = new Timer();
			def_T.schedule(new TimerTask() {
				@Override
				public void run() {
					engine.player.changeStr((-3));
					Engine.message = "New T-shirt: Strength down " + 3;
					
					return;
				}
			}, 1000*3);
			
			return 200;
		}
	}
	public String name() {
		return "T-Shirt";
	}
	public int defense () {
		return 1;
	}

	@Override
	public void setReadiness(int i) {
		// TODO Auto-generated method stub
		readiness = i;
	}

}
