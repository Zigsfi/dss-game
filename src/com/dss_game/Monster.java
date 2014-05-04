package com.dss_game;

import android.graphics.Canvas;

public interface Monster {
	public void init(Engine e);
	public void draw(Canvas c);
	public void update();
	public void take_dmg(int dmg);
	public void death();
	public int getExper();
	public int getDex();
}
