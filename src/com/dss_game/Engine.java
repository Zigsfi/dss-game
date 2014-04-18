package com.dss_game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.example.dss_game.R;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;

public class Engine extends SurfaceView implements Callback {
	int x = 0, y = 0;
	boolean click = false;
	Bitmap room, sword, shield;
	Monster monster;
	private String directory;
	Paint paint;
	SurfaceHolder surfaceholder;
	Player player;
	static Display display;
	static float scaleX;
	static float scaleY;
	public Engine(Context context, AttributeSet attrs) {
		super(context);
		getHolder().addCallback(this);
		setFocusable(true);
		setWillNotDraw(false);
		paint = new Paint();
		paint.setTextSize(50);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

		display = wm.getDefaultDisplay();
		scaleX = 1920.0f / (float)display.getWidth();
		scaleY = 1200.0f / (float)display.getWidth();
		//room = BitmapFactory.decodeFile(System.getProperty("user.id")+"/res/drawable-hdpi/room.png");
		System.out.println(Environment.getExternalStorageDirectory()+"/DSS-game/res/drawable-hdpi/room.png");
	}

	public void init(String directory) {


		room = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(getResources(), R.drawable.room), display.getWidth(), display.getHeight(), false);
		monster = new Demon();
		monster.init(this);
		player = new Player(this);
		surfaceholder = this.getHolder();
		new Thread() {
			public void run() {
				while (true) {
					update();
					repaint();
					try {
						sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	public void update() {
		handleInput();
	}
	
	public void handleInput() {
		if (click)
			player.handleInput(x, y);
		click = false;
	}
	public void repaint() {
		//	invalidate();
		monster.update();
		Canvas c = surfaceholder.lockCanvas();
		if (c != null) {
			paint(c);
			surfaceholder.unlockCanvasAndPost(c);
		}
	}
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

	}
	protected void paint(Canvas canvas) {
		//canvas.drawARGB(255, y % 255, x % 255, 255);
		try {
			canvas.drawBitmap(room, 0, 0, paint);
			monster.draw(canvas);
			player.draw(canvas);

		} catch (Exception e) {
			//	e.printStackTrace();
		}
	}
	public boolean onTouchEvent(MotionEvent event) {
		x=(int)event.getX(0);
		y=(int)event.getY(0);
		click = true;
		System.out.println("Click!!!");
		return super.onTouchEvent(event);

	}
}
