package com.lllwwwbbb.PlaneWar;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Missile {
	private final int WIDTH = 10, HEIGH = 20, SPE_HERO = 100, SPE_PLANE = 200;
	public static enum OBJ { HERO, PLANE }
	private OBJ obj;
	private double x, y, speed; // (x, y) is the position of the center of missile
	private Image image;
	private static Image img_hero = new ImageIcon("res/missile_hero.png").getImage();
	private static Image img_plane = new ImageIcon("res/missile_plane.png").getImage();
	
	
	public Missile(OBJ obj, double x, double y) {
		this.obj = obj;
		this.x = x;
		this.y = y;
		image = obj == OBJ.HERO ? img_hero : img_plane;
		speed = obj == OBJ.HERO ? SPE_HERO : SPE_PLANE;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, (int)x - WIDTH / 2, (int)y - HEIGH / 2, WIDTH, HEIGH, null); 
	}
	
	public void move() {
		if (obj == OBJ.HERO) {
			y -= speed / (1000.0 / Game.PERIOD);
		}
		else {
			y += speed / (1000.0 / Game.PERIOD);
		}
	}
	
	public boolean out() {
		return obj == OBJ.HERO ?
				y + HEIGH / 2 < 0 : y - HEIGH / 2 > Game.SCR_HEIGH;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public boolean isHero() {
		return obj == OBJ.HERO;
	}
}
