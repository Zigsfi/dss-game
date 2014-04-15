package com.dss_game;

import android.graphics.Canvas;

public interface Monster {
	public void init(Engine e);
	public void draw(Canvas c);
	public void update();
}
