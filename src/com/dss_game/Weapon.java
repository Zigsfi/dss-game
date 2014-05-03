package com.dss_game;

import android.graphics.Bitmap;
import android.graphics.Paint;

public interface Weapon {
	public String name();
	public Bitmap image();
	public int readiness();
	public void setReadiness(int i);
	public Bitmap menu(int x, int y, Paint p);
	public boolean tapped(int x, int y);
}
