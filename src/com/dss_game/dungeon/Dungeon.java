package com.dss_game.dungeon;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;

import com.dss_game.Engine;
import com.dss_game.GameMenu;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.dss_game.Action;
public class Dungeon {



	public LinkedList<Room> rooms;
	public boolean matrix[][];
	public Edge[] edges;
	public LinkedList<Edge> mst;
	public Room curRoom;
	Bitmap b;
	Engine engine;
	public Dungeon(Engine e) {
		rooms = new LinkedList<Room>();
		b = Bitmap.createBitmap((int)(5000 * Engine.scaleX), (int)(5000 * Engine.scaleY), Bitmap.Config.ARGB_8888);

		engine = e;
		generate();
	}
	public void generate() {
		LinkedList<Edge> edges = new LinkedList<Edge>();

		for (int i = 0; i < 180; i++) {
			Room r = new Room();
			ListIterator<Room> roomIt = rooms.listIterator();
			boolean fits = true;
			while (roomIt.hasNext()) {
				Room room = roomIt.next();
				if (Rect.intersects(r.rec, room.rec)) {
					fits = false;
				} else {
					//		edges.add(new Edge(r, room));
				}
			}
			if (fits) {
				rooms.add(r);
				if (r.drawrec.right < 1920 * Engine.scaleX && r.drawrec.top < 1200 * Engine.scaleY)
					curRoom = r;
				roomIt = rooms.listIterator();
				while (roomIt.hasNext()) {
					Room room = roomIt.next();
					if (r != room)
						edges.add(new Edge(r, room));

				}
			}
		}


		Object[] array = edges.toArray();
		Arrays.sort(array);
		this.edges = new Edge[array.length];
		mst = new LinkedList<Edge>();
		for (int i = 0; i < array.length; i++) {
			Edge e = (Edge) array[i];
			System.out.println(e.distance);
			this.edges[i] = e;
			if (e.a.sentinel != e.b.sentinel) {
				e.a.sentinel.addAll(e.b.sentinel);
				ListIterator<Room> edgeIt = e.b.sentinel.listIterator();
				while (edgeIt.hasNext()) {
					Room toChange = edgeIt.next();
					toChange.sentinel = e.a.sentinel;
				}
				mst.add(e);
			}
		}
		/*
		for (int i = 0; i < array.length/40; i++) {
			if (mst.indexOf((Edge) array[i]) == -1)
				mst.add((Edge) array[i]);
		}
		*/
		ListIterator<Edge> edgeIt = mst.listIterator();
		while (edgeIt.hasNext()) {
			Edge mstEdge = edgeIt.next();
			mstEdge.a.links.add(mstEdge.b);
			mstEdge.b.links.add(mstEdge.a);
		}
		curRoom.menu = new GameMenu();
		curRoom.menu.addOption("Dead Squirrel Story", null);
		curRoom.menu.addOption("By Will Hickey", null);
		curRoom.menu.addOption("	& Arthur Berman", null);
		curRoom.menu.addOption("Music by Thomas Colgrove", null);
		
	}

	public static double distance(Rect a, Rect b) {
		return Math.sqrt(Math.pow((float)(a.centerX() - b.centerX()), 2) + Math.pow((float)(a.centerY() - b.centerY()), 2));
	}
	public void tapped(int x, int y, int dX, int dY) {
		if (x < 400 && y < 300) {
			Action a = curRoom.getMenu().click(x, y);
			if (a != null)
				a.execute();
		}
		tappedRoom(x - dX, y - dY);
	}
	public void tappedRoom (int x, int y) {
		//	System.out.println("Balls");
		ListIterator<Room> roomIt = rooms.listIterator();
		curRoom.visited = true;
		while (roomIt.hasNext()) {
			Room r = roomIt.next();
			if (r.drawrec.contains(x, y)) {
				if (r.links.indexOf(curRoom) != -1 || r.visited) {
					curRoom = r;
					if (!r.visited) {
						engine.initFight(r);
						
						for (int i = 0; i < 25; i++) {
							Canvas c = engine.surfaceholder.lockCanvas();
							if (c != null) {
								engine.paint.setARGB(255, 255, 255, 255);
								c.drawRect(0, 0, 10000, 10000, engine.paint);
								engine.paint.setARGB(255, 0, 0, 0);
								c.drawRect((960 * Engine.scaleX) - (i * 10), (600 * Engine.scaleY) - i * 10, 960 + i * 20, 600 + i * 20, engine.paint);
								engine.surfaceholder.unlockCanvasAndPost(c);
							}
							try {
								Thread.sleep(5);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			//c.drawRect(r.drawrec, p);

		}		
	}

	public Bitmap render(){
		Canvas c = new Canvas(b);

		Paint p = new Paint();
		c.drawRect(0, 0, 5000, 5000, p);
		ListIterator<Room> roomIt = rooms.listIterator();
		curRoom.visited = true;
		while (roomIt.hasNext()) {
			Room r = roomIt.next();
			p.setARGB(255, 255, 0, 0);

			//		c.drawRect(r.drawrec, p);
			p.setARGB(255, 0, 0, 255);
			if (curRoom == r) {
				p.setARGB(255, 255, 255, 255);
			}
			if (r.links.indexOf(curRoom) != -1) {
				p.setARGB(255, 255, 0, 0);
			}
			if (r.visited) {
				c.drawRect(r.drawrec, p);
				p.setARGB(255, 0, 0, 0);
				c.drawLine(r.drawrec.left, r.drawrec.top, r.drawrec.left + r.drawrec.width(), r.drawrec.top, p);
				c.drawLine(r.drawrec.left + r.drawrec.width(), r.drawrec.top, r.drawrec.left + r.drawrec.width(), r.drawrec.top + r.drawrec.height(), p);
				c.drawLine(r.drawrec.left, r.drawrec.top + r.drawrec.height(), r.drawrec.left + r.drawrec.width(), r.drawrec.top + r.drawrec.height(), p);
				c.drawLine(r.drawrec.right, r.drawrec.top, r.drawrec.right, r.drawrec.bottom, p);
			}
		}
		p.setARGB(255, 255, 255, 255);
		Object[] as = mst.toArray();
		for (int i = 0; i < as.length; i++) {
			Edge e = (Edge) as[i];
			if (e.a.visited || e.b.visited) {
				p.setARGB(255, 255, 255, 255);

				c.drawLine(e.a.drawrec.centerX(), e.a.drawrec.centerY(), e.b.drawrec.centerX(), e.b.drawrec.centerY(), p);
				p.setARGB(100, 128, 128, 128);
				c.drawCircle(e.a.drawrec.centerX(), e.a.drawrec.centerY(), 30 * Engine.scaleX, p);
				c.drawCircle(e.b.drawrec.centerX(), e.b.drawrec.centerY(), 30 * Engine.scaleX, p);

			}
		}
		return b;
	}
	public class Edge implements Comparator<Edge>, Comparable<Edge> {
		Room a, b;
		public double distance;
		public Edge(Room a, Room b) {
			this.a = a;
			this.b = b;
			this.distance = Dungeon.distance(a.rec, b.rec);
		}
		@Override
		public int compare(Edge arg0, Edge arg1) {
			// TODO Auto-generated method stub
			Edge a = (Edge) arg0, b = (Edge) arg1;

			return (int) (a.distance - b.distance);
		}
		@Override
		public int compareTo(Edge arg0) {
			// TODO Auto-generated method stub
			if(this.distance < arg0.distance)
				return -1;
			if (this.distance == arg0.distance) 
				return 0;
			return 1;
		}
	}
}
