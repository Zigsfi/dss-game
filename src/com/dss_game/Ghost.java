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
	public void hardHit() {
		int fadeDex = (int) Engine.player.getDex() / 2;
		
		//attack the player
		changeDex(fadeDex);
		Engine.message = "Ghost fades: harder to hit ";
		image = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.ghost),(int)( 480 * Engine.scaleX), (int)(720 * Engine.scaleY), false);;
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
