package com.dss_game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import com.dss_game.dungeon.Dungeon;
import com.dss_game.dungeon.Room;
import com.example.dss_game.R;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Environment;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;

public class Engine extends SurfaceView implements Callback, OnGestureListener {
	int x = 0, y = 0;
	boolean click = false;
	Bitmap room, sword, shield;
	Monster monster;
	private String directory;
	public Paint paint;
	public SurfaceHolder surfaceholder;
	public static Player player;
	static Display display;
	public static float scaleX;
	public static float scaleY;
	public int dungeonX = 0, dungeonY = 0;
	public static String message;
	boolean fighting = false;
	public GameMenu mainMenu;
	public static float scaleX() {
		return scaleX;
	}
	public static float scaleY() {
		return scaleY;
	}

	public Engine(Context context, AttributeSet attrs) {
		super(context);
		getHolder().addCallback(this);
		setFocusable(true);
		setWillNotDraw(false);
		paint = new Paint();
		paint.setTextSize(50);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

		display = wm.getDefaultDisplay();
		scaleX = (float)display.getWidth() / 1920.0f;
		scaleY = (float)display.getHeight() / 1200.0f;
		player = new Player(this);
		mainMenu = new GameMenu();

		message = "";

		//room = BitmapFactory.decodeFile(System.getProperty("user.id")+"/res/drawable-hdpi/room.png");
		System.out.println(Environment.getExternalStorageDirectory()+"/DSS-game/res/drawable-hdpi/room.png");
	}
	public void init(String oscar){ 
		surfaceholder = this.getHolder();
		final Engine engine = this;
		new Thread() {


			private Room startRoom;

			public void run() {
				//initFight("");
				while (true) {
					Dungeon dungeon = new Dungeon(engine);
					startRoom = dungeon.curRoom;
					if (dungeon.curRoom.x > 1920 * scaleX) {
						dungeonX -= (int)(dungeon.curRoom.x - 1920 * scaleX);
					}
					if (dungeon.curRoom.y > 1200 * scaleY) {
						dungeonY -= (int)(dungeon.curRoom.y - 1200 * scaleY);
					}
					while (player.getHp() > 0) {
						handleDungeonInput(dungeon);
						dungeonPaint(dungeon, startRoom);
						try {
							sleep(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					player = new Player(engine);
					mainMenu = new GameMenu();

					message = "New Game";
				}
			}
		}.start();

	}

	public void dungeonPaint(Dungeon dungeon, Room startRoom) {
		Canvas c = surfaceholder.lockCanvas();
		if (c!=null) {
			paint.setARGB(255,0,0,0);
			c.drawRect(0, 0, (int) (1920 * scaleX), (int)(1200 * scaleY), paint);

			c.drawBitmap(dungeon.render(), dungeonX, dungeonY, paint);
			c.drawBitmap(dungeon.curRoom.getMenu().render((dungeon.curRoom == startRoom ? 800 : 400), 400, paint), 0, 0, paint);
			c.drawBitmap(player.menu.render((int)(800 * scaleX),(int)( player.menuHeight * scaleY), paint), 1920*scaleX - (800 * scaleX), 0, paint);
			paint.setARGB(255, 128, 128, 128);
			c.drawText(Engine.message, (960 * scaleX) - paint.measureText(message)/2, 1100 * scaleY, paint);

			surfaceholder.unlockCanvasAndPost(c);
		}
	}

	public void handleDungeonInput(Dungeon dungeon) {
		if (click) {
			if (y < scaleY * player.menuHeight && x > 1920 * scaleX - 800 * scaleX) {
				Action a = player.menu.click(x, y);
				if (a != null)
					a.execute();
			}
			dungeon.tapped(x, y, dungeonX, dungeonY);
			click = false;
		}
	}
	public void initFight(Room r) {
		paint.setARGB(255, 0, 0, 0);
		player.left.setReadiness(0);
		player.middle.setReadiness(0);
		player.right.setReadiness(0);
		message = "";
		for (int i = 0; i < 25; i++) {
			Canvas can = surfaceholder.lockCanvas();
			paint.setARGB(255, 0, 0, 0);
			if (can != null) {
				can.drawRect(0, 0, 10000, 10000, paint);

				paint.setARGB(255, 255, 255, 255);

				can.drawRect((960 * scaleX) - (i * 40), (600 * scaleY) - i * 40, (960 * scaleX) + i * 40, (600 * scaleY) + i * 40, paint);
				surfaceholder.unlockCanvasAndPost(can);
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		fighting = true;
		room = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(getResources(), R.drawable.room), display.getWidth(), display.getHeight(), false);
		monster = r.monster;
		monster.init(this);
		click = false;
		while (fighting && player.getHp() > 0) {
			update();
			repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		fighting = false;

		message = (player.getHp() > 0 ? "Victory with " + monster.getExper() +" experiance" : "Defeat");
		Timer def_T = new Timer();
		def_T.schedule(new TimerTask() {
			@Override
			public void run() {
				player.giveExp(monster.getExper());
				return;
			}
		}, 750);
		
	}
	public void update() {
		handleInput();
		player.update();
		monster.update();
		if (((Stats)monster).getHp() <= 0) {
			fighting = false;
		}
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

			paint.setARGB(255, 255, 0, 0);
			canvas.drawText("HP: " + player.getHp(), 1650 * scaleX, 100 * scaleY, paint);
			paint.setARGB(255, 255, 255, 255);
			canvas.drawText(Engine.message, (960 * scaleX) - paint.measureText(message)/2, 100 * scaleY, paint);


		} catch (Exception e) {
			//	e.printStackTrace();
		}
	}

	public boolean onTouchEvent(MotionEvent event) {

		int action = MotionEventCompat.getActionMasked(event);
		switch (action){
		case (MotionEvent.ACTION_DOWN) :
			x=(int)event.getRawX();
		y=(int)event.getRawY();
		return true;
		case (MotionEvent.ACTION_MOVE) :
			int ox=(int)event.getRawX();
		int oy=(int)event.getRawY();
		if (!fighting) {
			dungeonX -= x - ox;
			dungeonY -= y - oy;
		}
		y = oy;
		x = ox;
		return true;
		case (MotionEvent.ACTION_UP) :
			x=(int)event.getRawX();
		y=(int)event.getRawY();
		click = true;
		return true;
		default:
			return super.onTouchEvent(event);

		}

	}
	@Override
	public void onGesture(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub

	}
}
