package com.lllwwwbbb.PlaneWar;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import com.lllwwwbbb.PlaneWar.Missile.OBJ;

public class Plane {
	private final int WIDTH = 100, HEIGH = 66, SPE_L = 50, SPE_H = 100,
			NR_DIR = 3, FIRE_MILSEC = 1000;
	private double x, y, speed;
	private int tick;
	private Dir dir;
	private static Image image = new ImageIcon("res/plane.png").getImage();

	public Plane() {
		x = (Math.random() * (Game.SCR_WIDTH - WIDTH));
		y = -HEIGH;
		speed = (Math.random() * (SPE_H - SPE_L) + SPE_L);
		dir = Dir.values()[(int)(Math.random() * NR_DIR)];
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, (int)x, (int)y, WIDTH, HEIGH, null);
	}
	
	public void move() {
		tick += Game.PERIOD;
		switch (dir) {
		case STRAIGHT:
			y += speed / (1000.0 / Game.PERIOD);
			break;
		case LEFT:
			x -= speed / (1000.0 / Game.PERIOD);
			y += speed / (1000.0 / Game.PERIOD);
			if (x < 0) {
				x = 0; 
				dir = Dir.RIGHT; 
			}
			break;
		case RIGHT:
			x += speed / (1000.0 / Game.PERIOD);
			y += speed / (1000.0 / Game.PERIOD);
			if (x + WIDTH > Game.SCR_WIDTH) {
				x = Game.SCR_WIDTH - WIDTH;
				dir = Dir.LEFT; 
			}
			break;
		default:
			break; 
		}
	}
	
	public void fire(LinkedList<Missile> missiles) {
		if (tick < FIRE_MILSEC) { return; }
		tick = 0;
		missiles.add(new Missile(OBJ.PLANE, x + WIDTH / 2, y + HEIGH));
	}
	
	public boolean out() {
		return y > Game.SCR_HEIGH;
	}
	
	public Point[] getPoint() {
		Point[] points = new Point[4];
		points[0] = new Point((int)x, (int)y + HEIGH / 4);
		points[1] = new Point((int)x, (int)y + HEIGH);
		points[2] = new Point((int)x + WIDTH, (int)y + HEIGH / 4);
		points[3] = new Point((int)x + WIDTH, (int)y + HEIGH);
		return points;
	}
	
	public boolean hit(double x, double y) {
		return x > this.x && x < this.x + WIDTH &&
				y > this.y + HEIGH / 4 && y < this.y + HEIGH;
	}
	
	public void explode() {
		
	}
}

enum Dir { STRAIGHT, LEFT, RIGHT }
