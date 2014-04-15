package com.dss_game;

import android.graphics.Bitmap;
import android.graphics.Paint;

public interface Weapon {
	public Bitmap image();
	public int readiness();
	public Bitmap menu(Paint p);
	public boolean tapped(int x, int y);
}
