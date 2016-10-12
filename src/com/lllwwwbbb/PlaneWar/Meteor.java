package com.lllwwwbbb.PlaneWar;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Meteor {
	private final int WIDTH = 80, HEIGH = 100, SPE_L = 50, SPE_H = 100;
	private double x, y, speed;
	private static Image image = new ImageIcon("res/meteor.png").getImage();

	public Meteor() {
		x = (Math.random() * (Game.SCR_WIDTH - WIDTH));
		y = -HEIGH;
		speed = (Math.random() * (SPE_H - SPE_L) + SPE_L);
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, (int)x, (int)y, WIDTH, HEIGH, null);
	}
	
	public void move() {
		y += speed / (1000.0 / Game.PERIOD);
	}
	
	public boolean out() {
		return y > Game.SCR_HEIGH;
	}
	
	public Point[] getPoint() {
		Point[] points = new Point[4];
		points[0] = new Point((int)x, (int)y);
		points[1] = new Point((int)x, (int)y + HEIGH);
		points[2] = new Point((int)x + WIDTH, (int)y);
		points[3] = new Point((int)x + WIDTH, (int)y + HEIGH);
		return points;
	}
	
	public void explode() {
		
	}
}

