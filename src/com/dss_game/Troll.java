package com.dss_game;

import com.example.dss_game.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Troll implements Monster, Stats {
	Engine engine;
	Bitmap image;
	int x, y;
	int Hp, Mp, Str, Def, Dex, IQ;
	public Troll() {
		x = 900;
		y = 500;
	}

	@Override
	public void init(Engine e) {
		this.engine = e;
		image = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(this.engine.getResources(), R.drawable.monster),(int)( 240 * Engine.scaleX), (int)(320 * Engine.scaleY), false);
	}

	@Override
	public void draw(Canvas c) {
		c.drawBitmap(image, (int) (Engine.scaleX * x), (int)(Engine.scaleY * y), engine.paint);
	}

	@Override
	public void update() {
		if (Hp <= 0) {
			death();
			
		}

	}

	@Override
	public int getHp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHp(int curHp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeHp(int addHp) {
		// TODO Auto-generated method stub
		
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
		return 0;
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
		changeHp( dmg);
		
	}

	@Override
	public void death() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getExper() {
		// TODO Auto-generated method stub
		return 0;
	}

}
