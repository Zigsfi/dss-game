package com.dss_game;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

import com.example.dss_game.R;

public class DeadSquirrel extends Enemy {
	public void init(Engine e) {
		this.engine = e;
		Options options = new BitmapFactory.Options();
		options.inScaled = false;
		normImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.deadsquirrel, options),(int)( 480 * Engine.scaleX), (int)(720 * Engine.scaleY), false);
		hitImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.monster_hit),(int)( 240 * Engine.scaleX), (int)(320 * Engine.scaleY), false);
		missImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.deadsquirrel, options),(int)( 210 * Engine.scaleX), (int)(330 * Engine.scaleY), false);
		image = normImage;
		x = 700;
		y = 300;
		Hp = 100;
		Mp = 10;
		Str = 20;
		Def = 6;
		Dex = 5;
		IQ = 5;
	}
	public void hardHit() {
		int damage = Str - Engine.player.getDef() + ((int)(Str/2));
		if(damage <= 0) {
			damage = 1;
		}
		//attack the player
		Engine.player.changeHp(-1 * damage);
		Engine.message = "Dead Squirrel hits hard for " + damage;
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
		sAttack = 700;
	}
}
