package com.lllwwwbbb.PlaneWar;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import com.lllwwwbbb.PlaneWar.Missile.OBJ;


public class Hero {
	private final int WIDTH = 80, HEIGH = 100, SPEED = 200, FULL_LIFE = 5;
	private double x, y;
	private int life;
	private boolean up, down, left, right;
	private Image image;
	
	public Hero() {
		life = FULL_LIFE;
		image = new ImageIcon("res/hero.png").getImage();
		x = (Game.SCR_WIDTH - WIDTH) / 2;
		y = Game.SCR_HEIGH - HEIGH * 2;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, (int)x, (int)y, WIDTH, HEIGH, null);
	}

	public void move() {
		if (up) { y -=  SPEED / (1000.0 / Game.PERIOD); }
		if (down) { y +=  SPEED / (1000.0 / Game.PERIOD); }
		if (left) { x -= SPEED / (1000.0 / Game.PERIOD); }
		if (right) { x += SPEED / (1000.0 / Game.PERIOD); }
		
		if (x < 0) { x = 0; }
		if (y < 0) { y = 0; }
		if (x + WIDTH > Game.SCR_WIDTH) { x = Game.SCR_WIDTH - WIDTH; }
		if (y + HEIGH > Game.SCR_HEIGH) { y = Game.SCR_HEIGH - HEIGH; }
	}
	
	public void fire(LinkedList<Missile> missiles) {
		missiles.add(new Missile(OBJ.HERO, x + WIDTH / 2, y));
	}
	
	public boolean hit(double x, double y) {
		boolean h = x > this.x && x < this.x + WIDTH &&
				y > this.y + HEIGH / 4 && y < this.y + HEIGH;
		if (h) {
			life --;
		}
		return h;
	}
	
	public boolean collide(Point[] points) {
		for (Point point : points) {
			if (hit(point.x, point.y)){
				return true;
			}
		}
		return false;
	}
	
	public void explode(LinkedList<Explode> explodes) {
		explodes.add(new Explode(x + WIDTH / 2, y + HEIGH / 2));
	}
	
	public boolean live() {
		return life > 0;
	}
	
	public int getLife() {
		return life;
	}
	
	public void setDir(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_UP:	up = true;		break;
		case KeyEvent.VK_DOWN:	down = true;	break;
		case KeyEvent.VK_LEFT:	left = true;	break;
		case KeyEvent.VK_RIGHT:	right = true;	break;
		
		default:
			break;
		}
	}
	
	public void unsetDir(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_UP:	up = false;		break;
		case KeyEvent.VK_DOWN:	down = false;	break;
		case KeyEvent.VK_LEFT:	left = false;	break;
		case KeyEvent.VK_RIGHT:	right = false;	break;
		
		default:
			break;
		}
	}
}
