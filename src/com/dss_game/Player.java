package com.dss_game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Player implements Stats {
	Engine engine;
	Weapon left, right;
	Armor middle;
	int Hp;
	int Mp;
	int Str;
	int Def;
	int Dex;
	int IQ /*Intellect*/;
	int Exp;

	public Player(Engine e) {
		engine = e;
		right = new Sword(e);
		left = new Shield(e);
		middle = new T_shirt(e);
		Hp = 100;
		Mp = 100;
		Str = 10; /* + swordStats*/
		Def = 10; /* + shiedStats*/
		Dex = 10; /* + armorStats - shieldStats*/
		IQ = 10; /* + rightStats*/

	}

	public void update() {
		if (Hp <= 0){
			System.out.println("The player is dead!...");
		}
	}

	public void draw (Canvas c) {
		c.drawBitmap(right.image(), (int)(1280 * Engine.scaleX), (int)((800+right.readiness()) * Engine.scaleY), engine.paint);
		c.drawBitmap(left.image(), (int)(300 * Engine.scaleX), (int)((600+left.readiness()) * Engine.scaleY), engine.paint);
		int left_x, left_y, right_x, right_y, mid_x, mid_y;
		left_x = (int)(200 * Engine.scaleX);
		left_y = (int)((900+left.readiness()) * Engine.scaleY);
		c.drawBitmap(left.menu(400, left_y, engine.paint), left_x, left_y, engine.paint);
		mid_x = (int)(700 * Engine.scaleX);
		mid_y = (int)((900+middle.readiness()) * Engine.scaleY);
		c.drawBitmap(middle.menu(mid_x, mid_y,  engine.paint), mid_x, mid_y, engine.paint);
		right_x = (int)(1280 * Engine.scaleX);
		right_y = (int)((900+right.readiness()) * Engine.scaleY);
		c.drawBitmap(right.menu(right_x, right_y, engine.paint), right_x, right_y, engine.paint);

		//TODO health and magic status in top of screen
	}

	public void handleInput(int x, int y) {
		System.out.println("Handling");
		int trisect_screen = Engine.display.getWidth() / 3;
		if (y > 800 * Engine.scaleX) {
			if (x < trisect_screen) {
				left.tapped(x, y);
			} else if(x < (2*trisect_screen)) {
				middle.tapped(x, y);			
			} else {
				right.tapped(x, y);
			}
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
		System.out.println( String.format("Player Str: %d", Str));

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

	public void give(int i) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
