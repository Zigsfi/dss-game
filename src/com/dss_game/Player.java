package com.dss_game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Player implements Stats {
	Engine engine;
	Weapon left, right;
	int Hp;
	int Mp;
	int Str;
	int Def;
	int Dex;
	int IQ /*Intellect*/;
	
	public Player(Engine e) {
		engine = e;
		right = new Sword(e);
		left = new Shield(e);
		Hp = 100;
		Mp = 100;
		Str = 10; /* + swordStats*/
		Def = 10; /* + shiedStats*/
		Dex = 10; /* + armorStats - shieldStats*/
		IQ = 10; /* + rightStats*/
		
	}

	public void update() {
	}

	public void draw (Canvas c) {
		c.drawBitmap(right.image(), (int)(1050 * Engine.scaleX), (int)((800+right.readiness()) * Engine.scaleY), engine.paint);
		c.drawBitmap(left.image(), (int)(400 * Engine.scaleX), (int)((600+left.readiness()) * Engine.scaleY), engine.paint);
		int x, y;
		x = (int)(200 * Engine.scaleX);
		y = (int)((900+left.readiness()) * Engine.scaleY);
		c.drawBitmap(left.menu(x, y, engine.paint), x, y, engine.paint);
	}

	public void handleInput(int x, int y) {
		System.out.println("Handling");
		if (x < Engine.display.getWidth() / 2) {
			left. tapped(x, y);
		} else {
			right.tapped(x, y);
		}

	}

	@Override
	public int getHp() {
		// TODO Auto-generated method stub
		return Hp;
	}

	@Override
	public void setHp(int curHp) {
		Hp = curHp;
		
	}

	@Override
	public void changeHp(int addHp) {
		Hp = Hp + addHp;
		
	}

	@Override
	public int getMp() {
		// TODO Auto-generated method stub
		return Mp;
	}

	@Override
	public void setMp(int curMp) {
		Mp = curMp;
		
	}

	@Override
	public void changeMp(int addMp) {
		Mp = Mp + addMp;
		
	}

	@Override
	public int getStr() {
		// TODO Auto-generated method stub
		return Str;
	}

	@Override
	public void setStr(int curStr) {
		Str = curStr;
		
	}

	@Override
	public void changeStr(int addStr) {
		Str = Str + addStr;
		
	}

	@Override
	public int getDef() {
		// TODO Auto-generated method stub
		return Def;
	}

	@Override
	public void setDef(int curDef) {
		Def = curDef;
		
	}

	@Override
	public void changeDef(int addDef) {
		Def = Def + addDef;
		System.out.println( String.format("Player Def: %d", Def));
		
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
}
