package com.dss_game;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

import com.example.dss_game.R;

public class Slime extends Enemy {
	public Slime (){
		super();
		x = 900;
		y = 500;
		Hp = 30;
		Mp = 10;
		Str = 20;
		Def = 10;
		Dex = 4;
		IQ = 5;
	}
	
	@Override
	public void init(Engine e) {
		this.engine = e;
		Options options = new BitmapFactory.Options();
		options.inScaled = false;
		normImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.slime, options),(int)( 240 * Engine.scaleX), (int)(360 * Engine.scaleY), false);
		hitImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.monster_hit),(int)( 240 * Engine.scaleX), (int)(320 * Engine.scaleY), false);
		missImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.slime, options),(int)( 210 * Engine.scaleX), (int)(330 * Engine.scaleY), false);
		image = normImage;
	}
	@Override
	public void update() {
		if (Hp <= 0){
			death();
		}
		if(readiness <= 0){
			int damage = Str - Engine.player.getDef();
			if(damage <= 0) {
				damage = 0;
			}
			
			//check dex hit chance
			int pdex = Engine.player.getDex();
			int mydex = ((int)(Math.random() * pdex)) + Dex;
			
			if (pdex - mydex >= 0) {
				Engine.message = "Slime missed";
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
			}
			else {			
				Engine.player.changeHp(-1 * damage);
				Engine.message = "Slime smash did " + damage;
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
		
	}
}
