package com.dss_game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

import com.example.dss_game.R;

public class Spider extends Enemy {
	@Override
	public void init(Engine e) {
		this.engine = e;
		Options options = new BitmapFactory.Options();
		options.inScaled = false;
		normImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.spider, options),(int)( 480 * Engine.scaleX), (int)(720 * Engine.scaleY), false);
		hitImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.monster_hit),(int)( 240 * Engine.scaleX), (int)(320 * Engine.scaleY), false);
		image = normImage;
	}
}
