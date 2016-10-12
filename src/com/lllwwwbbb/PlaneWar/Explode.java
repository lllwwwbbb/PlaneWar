package com.lllwwwbbb.PlaneWar;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Explode {
	private static final int NR_IMG = 5, TIME = 500, WIDTH = 50, HEIGH = 50;
	private double x, y;
	private int tick = 0;
	private Image[] images;
	
	public Explode(double x, double y) {
		this.x = x;
		this.y = y;
		if (images == null) {
			images = new Image[NR_IMG];
			for (int i = 0; i < NR_IMG; i ++) {
				images[i] = new ImageIcon("res/explode_" + i + ".png").getImage();
			}
		}
	}
	
	public void move() {
		tick += Game.PERIOD;
	}
	
	public void draw(Graphics2D g2d) {
		int n = tick / (TIME / NR_IMG);
		g2d.drawImage(images[n], (int)x - WIDTH / 2, (int)y - HEIGH / 2, 
				WIDTH, HEIGH, null);
	}
	
	public boolean out() {
		return tick >= 500;
	}
}
