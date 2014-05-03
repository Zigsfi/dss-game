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
		
		Options options = new BitmapFactory.Options();
		options.inScaled = false;
		normImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.ghost, options),(int)( 480 * Engine.scaleX), (int)(720 * Engine.scaleY), false);
		hitImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.monster_hit),(int)( 240 * Engine.scaleX), (int)(320 * Engine.scaleY), false);
		missImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.ghost, options),(int)( 210 * Engine.scaleX), (int)(330 * Engine.scaleY), false);
		image = normImage;
	}
	
	public void hardHit() {
		int damage = Str - Engine.player.getDef() + ((int)(Str/2));
		if(damage <= 0) {
			damage = 1;
		}
		//attack the player
		Engine.player.changeHp(-1 * damage);
		Engine.message = "Enemey hits hard for " + damage;
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
