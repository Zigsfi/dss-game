package com.dss_game;

import java.util.Timer;
import java.util.TimerTask;

import com.example.dss_game.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;

public class Enemy implements Monster, Stats {
	Engine engine;
	Bitmap image;
	Bitmap hitImage;
	Bitmap normImage;
	Bitmap missImage;
	int x, y;
	int Hp, Mp, Str, Def, Dex, IQ;
	int exper = 5;
	
	int readiness = 200;
	int sAttack = 400;
	private int hit; 
	public Enemy() {
		x = 900;
		y = 500;

		Hp = 30;
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
		x += (hit > 0 ? ((hit-- % 4) - 2) * 3 : 0);
		c.drawBitmap(image, (int) (Engine.scaleX * x), (int)(Engine.scaleY * y), engine.paint);
	}

	@Override
	public void update() {
		
		if(sAttack <= 0){
			hardHit();
			//sAttack = 700;
			readiness = 200;
		}else if(readiness <= 0){
			int damage = Str - Engine.player.getDef();
			if(damage <= 0) {
				damage = 1;
			}
				//check dex hit chance
				int pdex = Engine.player.getDex();
				int mydex = ((int)(Math.random() * pdex)) + Dex;
				
				if (pdex - mydex >= 0) {
					Engine.message = "Enemy missed";
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
				}else {
					//attack the player
					Engine.player.changeHp(-1 * damage);
					Engine.message = "Enemy attacks for " + damage;
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
		sAttack--;
		if (Hp <= 0){
			death();
		}
		
	}

	@Override
	public int getHp() {
		// TODO Auto-generated method stub
		return Hp;
	}

	@Override
	public void setHp(int curHp) {
		// TODO Auto-generated method stub
		Hp = curHp;
	}

	@Override
	public void changeHp(int addHp) {
		// TODO Auto-generated method stub
		Hp = Hp + addHp;
		
	}

	@Override
	public int getMp() {
		// TODO Auto-generated method stub
		return Mp;
	}

	@Override
	public void setMp(int curMp) {
		// TODO Auto-generated method stub
		Mp = curMp;
	}

	@Override
	public void changeMp(int addMp) {
		// TODO Auto-generated method stub
		Mp = Mp + addMp;
	}

	@Override
	public int getStr() {
		// TODO Auto-generated method stub
		return Str;
	}

	@Override
	public void setStr(int curStr) {
		// TODO Auto-generated method stub
		Def = curStr;
	}

	@Override
	public void changeStr(int addStr) {
		// TODO Auto-generated method stub
		Str = Str + addStr;
	}

	@Override
	public int getDef() {
		// TODO Auto-generated method stub
		return Def;
	}

	@Override
	public void setDef(int curDef) {
		// TODO Auto-generated method stub
		Def = curDef;
	}

	@Override
	public void changeDef(int addDef) {
		// TODO Auto-generated method stub
		Def = Def + addDef;
	}

	@Override
	public int getDex() {
		// TODO Auto-generated method stub
		return Dex;
	}

	@Override
	public void setDex(int curDex) {
		// TODO Auto-generated method stub
		Dex = curDex;
	}

	@Override
	public void changeDex(int addDex) {
		// TODO Auto-generated method stub
		Dex = Dex + addDex;
		
	}

	@Override
	public int getInt() {
		// TODO Auto-generated method stub
		return IQ;
	}

	@Override
	public void setInt(int curInt) {
		// TODO Auto-generated method stub
		IQ = curInt;
	}

	@Override
	public void changeInt(int addInt) {
		// TODO Auto-generated method stub
		IQ = IQ + addInt;
		
	}

	@Override
	public void take_dmg(int dmg) {
		changeHp((dmg+Def));
		System.out.println( String.format("Deamon HP: %d", Hp));
		Engine.sounds.play(Engine.soundId[1], 0.5f, 0.5f, 1, 0, 2);

		hit = 15;
	}
	
	@Override
	public int getExper(){
		return exper;
	}

	@Override
	public void death() {
		// TODO Auto-generated method stub
		Hp = 0;
		//Engine.player.giveExp(exper);
		Engine.sounds.play(Engine.soundId[2], 0.5f, 0.5f, 1, 0, 1);
		//Engine.message = "You got " + exper + " Experiance.";
		
		
	}
	public void hardHit() {
		int damage = Str - Engine.player.getDef() + ((int)(Str/2));
		if(damage <= 0) {
			damage = 1;
		}
		//attack the player
		Engine.player.changeHp(-1 * damage);
		Engine.message = "Enemy hits hard for " + damage;
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
