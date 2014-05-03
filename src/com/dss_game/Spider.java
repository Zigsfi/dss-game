package com.dss_game;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

import com.example.dss_game.R;

public class Spider extends Enemy {
	@Override
	public void init(Engine e) {
		this.engine = e;
		
		exper = 10;
		
		Options options = new BitmapFactory.Options();
		options.inScaled = false;
		normImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.spider, options),(int)( 480 * Engine.scaleX), (int)(720 * Engine.scaleY), false);
		hitImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.monster_hit),(int)( 240 * Engine.scaleX), (int)(320 * Engine.scaleY), false);
		missImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.spider, options),(int)( 210 * Engine.scaleX), (int)(330 * Engine.scaleY), false);
		image = normImage;
	}
	
	@Override
	public void hardHit() {
		int webworks = (int) Engine.player.getDex() / 2;
		if(Engine.player.getDex() <= 0) {
			webworks = 0;
		}
		//attack the player
		Engine.player.changeDex(-1 * webworks);
		Engine.message = "Spider webs you: Dex down " + webworks;
		image = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.spiderweb),(int)( 480 * Engine.scaleX), (int)(720 * Engine.scaleY), false);;
		Timer def_T = new Timer();
		def_T.schedule(new TimerTask() {
			@Override
			public void run() {
				image = normImage;
				int web = (int) Engine.player.getDex() * 2;
				Engine.player.changeDex(web);
				Engine.message = "Cleared the web: Dex up " + web;
				return;
			}
		}, 4000);
		sAttack = 2000 - (int)(Math.random() * 1000);
	}

}
