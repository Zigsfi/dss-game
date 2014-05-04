package com.dss_game;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

import com.example.dss_game.R;

public class Ghost extends Enemy{
	@Override
	public void init(Engine e) {
		this.engine = e;
		Hp = 20;
		Mp = 10;
		Str = 10;
		Def = 3;
		Dex = 10;
		IQ = 10;
		exper = 10;
		
		Options options = new BitmapFactory.Options();
		options.inScaled = false;
		normImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.ghost, options),(int)( 480 * Engine.scaleX), (int)(720 * Engine.scaleY), false);
		hitImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.ghost),(int)( 600 * Engine.scaleX), (int)(840 * Engine.scaleY), false);
		missImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.ghost, options),(int)( 210 * Engine.scaleX), (int)(330 * Engine.scaleY), false);
		image = normImage;
	}
	
	@Override
	public void update() {
		
		if(sAttack <= 0){
			hardHit();
			//sAttack = 700;
			readiness = 200;
		}else if(readiness <= 0){
			int damage = Str - Engine.player.getDef();
			if(damage <= 0) {
				damage = 1;
			}
				//check dex hit chance
				int pdex = Engine.player.getDex();
				int mydex = ((int)(Math.random() * pdex)) + Dex;
				Dex = 10;
				if (pdex - mydex >= 0) {
					Engine.message = "Enemy missed";
					image = missImage;
					Timer def_T = new Timer();
					def_T.schedule(new TimerTask() {
						@Override
						public void run() {
							image = normImage;
							Engine.message = "";
							return;
						}
					}, 500);
					readiness = 200;
				}else {
					//attack the player
					Engine.player.changeHp(-1 * damage);
					Engine.message = "Enemy attacks for " + damage;
					image = hitImage;
					Timer def_T = new Timer();
					def_T.schedule(new TimerTask() {
						@Override
						public void run() {
							image = normImage;
							Engine.message = "";
							return;
						}
					}, 750);
					readiness = 200;
				}
		}
		readiness--;
		sAttack--;
		if (Hp <= 0){
			death();
		}
		
	}
	
	@Override
	public void hardHit() {
		int fadeDex = (int) Engine.player.getDex() / 2;
		
		//attack the player
		changeDex(fadeDex);
		Engine.message = "Ghost fades: harder to hit ";
		image = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.ghost),(int)( 480 * Engine.scaleX), (int)(720 * Engine.scaleY), false);
		Timer def_T = new Timer();
		def_T.schedule(new TimerTask() {
			@Override
			public void run() {
				image = normImage;;
				Engine.player.setDex(10);
				Engine.message = "Ghost reappeared.";
				return;
			}
		}, 4000);
		sAttack = 2000 - (int)(Math.random() * 1000);
	}


}
