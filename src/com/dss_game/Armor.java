package com.dss_game;

import android.graphics.Bitmap;
import android.graphics.Paint;

public interface Armor {
	public int readiness();
	public int defense();
	public Bitmap menu(int x, int y, Paint p);
	public boolean tapped(int x, int y);
	public void setReadiness(int i);
	public String name();

}
