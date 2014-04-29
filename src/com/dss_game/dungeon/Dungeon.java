package com.dss_game.dungeon;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Dungeon {



	public LinkedList<Room> rooms;
	public boolean matrix[][];
	public Edge[] edges;
	public LinkedList<Edge> mst;
	public Room curRoom;
	public Dungeon() {
		rooms = new LinkedList<Room>();

		generate();
	}
	public void generate() {
		LinkedList<Edge> edges = new LinkedList<Edge>();

		for (int i = 0; i < 80; i++) {
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
		ListIterator<Edge> edgeIt = mst.listIterator();
		while (edgeIt.hasNext()) {
			Edge mstEdge = edgeIt.next();
			mstEdge.a.links.add(mstEdge.b);
			mstEdge.b.links.add(mstEdge.a);
		}
	}

	public static double distance(Rect a, Rect b) {
		return Math.sqrt(Math.pow((float)(a.centerX() - b.centerX()), 2) + Math.pow((float)(a.centerY() - b.centerY()), 2));
	}

	public void tapped (int x, int y) {
		System.out.println("Balls");
		ListIterator<Room> roomIt = rooms.listIterator();
		curRoom.visited = true;
		while (roomIt.hasNext()) {
			Room r = roomIt.next();
			if (r.drawrec.contains(x, y)) {
				if (r.links.indexOf(curRoom) != -1) {
					curRoom = r;
				}
			}
			//c.drawRect(r.drawrec, p);

		}		
	}

	public Bitmap render(){
		Bitmap b = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(b);
		Paint p = new Paint();
		ListIterator<Room> roomIt = rooms.listIterator();
		curRoom.visited = true;
		while (roomIt.hasNext()) {
			Room r = roomIt.next();
			p.setARGB(255, 255, 0, 0);

			//c.drawRect(r.drawrec, p);
			p.setARGB(255, 0, 0, 255);
			if (curRoom == r) {
				p.setARGB(255, 255, 255, 255);
			}
			if (r.links.indexOf(curRoom) != -1) {
				p.setARGB(255, 255, 0, 0);
			}
			if (r.visited)
				c.drawRect(r.drawrec, p);

		}
		p.setARGB(255, 255, 255, 255);
		Object[] as = mst.toArray();
		for (int i = 0; i < as.length; i++) {
			Edge e = (Edge) as[i];
			if (e.a.visited || e.b.visited)
				c.drawLine(e.a.drawrec.centerX(), e.a.drawrec.centerY(), e.b.drawrec.centerX(), e.b.drawrec.centerY(), p);
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
