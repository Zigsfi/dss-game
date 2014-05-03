package com.dss_game;

import java.util.Timer;
import java.util.TimerTask;

import com.example.dss_game.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;

public class Demon implements Monster, Stats {
	Engine engine;
	Bitmap image;
	Bitmap hitImage;
	Bitmap normImage;
	Bitmap missImage;
	int x, y;
	int Hp, Mp, Str, Def, Dex, IQ;
	int readiness = 200;
	public Demon() {
		x = 900;
		y = 500;
		Hp = 10;
		Mp = 10;
		Str = 20;
		Def = 3;
		Dex = 5;
		IQ = 5;
	}

	@Override
	public void init(Engine e) {
		this.engine = e;
		Options options = new BitmapFactory.Options();
		options.inScaled = false;
		normImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.imp, options),(int)( 240 * Engine.scaleX), (int)(360 * Engine.scaleY), false);
		hitImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.monster_hit),(int)( 240 * Engine.scaleX), (int)(320 * Engine.scaleY), false);
		missImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.imp, options),(int)( 210 * Engine.scaleX), (int)(330 * Engine.scaleY), false);
		image = normImage;
	}

	@Override
	public void draw(Canvas c) {
		c.drawBitmap(image, (int) (Engine.scaleX * x), (int)(Engine.scaleY * y), engine.paint);
	}

	@Override
	public void update() {
		if (Hp <= 0){
			death();
		}
		if(readiness <= 0){
			int damage = Str - Engine.player.getDef();
			if(damage <= 0) {
				damage = 1;
			}
			//check dex hit chance
			int pdex = Engine.player.getDex();
			int mydex = ((int)(Math.random() * pdex)) + Dex;
			
			if (pdex - mydex >= 0) {
				Engine.message = "Demon missed";
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
				Engine.message = "Demon cut did " + damage;
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

	@Override
	public int getHp() {
		// TODO Auto-generated method stub
		return Hp;
	}

	@Override
	public void setHp(int curHp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeHp(int addHp) {
		// TODO Auto-generated method stub
		Hp = Hp + addHp;
		
	}

	@Override
	public int getMp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMp(int curMp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeMp(int addMp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getStr() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setStr(int curStr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeStr(int addStr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDef() {
		// TODO Auto-generated method stub
		return Def;
	}

	@Override
	public void setDef(int curDef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeDef(int addDef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setDex(int curDex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeDex(int addDex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getInt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setInt(int curInt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeInt(int addInt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void take_dmg(int dmg) {
		changeHp((dmg+Def));
		System.out.println( String.format("Deamon HP: %d", Hp));
		
	}

	@Override
	public void death() {
		// TODO Auto-generated method stub
		Hp = 0;
		
		
		
	}

}
