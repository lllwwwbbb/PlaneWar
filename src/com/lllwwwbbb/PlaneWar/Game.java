package com.lllwwwbbb.PlaneWar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Game extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4397094568361722032L;
	public static final int SCR_WIDTH = 580, SCR_HEIGH = 680, PERIOD = 5,
			INIT_PLANE = 1, CREATE_MILSEC = 1000, METEOR_MILSEC = 2000;
	private int tick, tick_meteor;
	private Image image, bg;
	private Hero hero = new Hero();
	private LinkedList<Plane> planes = new LinkedList<Plane>();
	private LinkedList<Missile> missiles = new LinkedList<Missile>();
	private LinkedList<Meteor> meteors = new LinkedList<Meteor>();
	
	public Game() {
		Dimension scrDimension = new Dimension(SCR_WIDTH, SCR_HEIGH);
		setPreferredSize(scrDimension);
		setSize(scrDimension);
		
		bg = new ImageIcon("res/bg.jpg").getImage();
		for (int i = 0; i < INIT_PLANE; i ++) { planes.add(new Plane()); }
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				hero.move();
				for (int i = 0; i < planes.size(); i ++) { 
					planes.get(i).move();
					planes.get(i).fire(missiles);
					if (planes.get(i).out()) {
						planes.remove(i);
						i --;
						continue;
					}
					if (hero.collide(planes.get(i).getPoint())) {
						planes.get(i).explode();
						hero.explode();
						planes.remove(i);
						i --;
						continue;
					}
				}
				for (int i = 0; i < meteors.size(); i ++) {
					meteors.get(i).move();
					if (meteors.get(i).out()) {
						meteors.remove(i);
						i --;
						continue;
					}
					if (hero.collide(meteors.get(i).getPoint())) {
						meteors.get(i).explode();
						hero.explode();
						meteors.remove(i);
						i --;
						continue;
					}
				}
				for (int i = 0; i < missiles.size(); i ++) {
					Missile missile = missiles.get(i);
					missile.move();
					if (missile.out()) {
						missiles.remove(i);
						i --;
						continue;
					}
					if (missile.isHero() == false) {
						if (hero.hit(missile.getX(), missile.getY())) {
							hero.explode();
							missiles.remove(i);
							i --;
						}
						continue;
					}
					for (int j = 0; j < planes.size(); j ++) {
						if (planes.get(j).hit(missile.getX(), missile.getY())) {
							planes.get(j).explode();
							planes.remove(j);
							missiles.remove(i);
							i --;
							break;
						}
					}
				}
				tick += PERIOD;
				if (tick > CREATE_MILSEC) {
					tick = 0;
					planes.addLast(new Plane());
					planes.getLast().fire(missiles);
				}
				tick_meteor += PERIOD;
				if (tick_meteor > METEOR_MILSEC) {
					tick_meteor = 0;
					meteors.add(new Meteor());
				}
				repaint();
			}
		}, 0, PERIOD);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch (keyCode) {
				case KeyEvent.VK_UP:
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_RIGHT:		hero.setDir(keyCode);	break;
				default: break;
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch (keyCode) {
				case KeyEvent.VK_UP:
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_RIGHT:		hero.unsetDir(keyCode);	break;
				case KeyEvent.VK_SPACE:		hero.fire(missiles);	break;
				default: break;
				}
			}
		});
	}
	
	public void paint(Graphics g) {
		if (image == null) {
			image = createImage(SCR_WIDTH, SCR_HEIGH);
		}
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.drawImage(bg, 0, 0, SCR_WIDTH, SCR_HEIGH, null);
		hero.draw(g2d);
		for (int i = 0; i < planes.size(); i ++) { planes.get(i).draw(g2d); }
		for (int i = 0; i < missiles.size(); i ++) { missiles.get(i).draw(g2d); }
		for (int i = 0; i < meteors.size(); i ++) { meteors.get(i).draw(g2d); }
		g2d.setFont(new Font("宋体", Font.BOLD, 23));
		g2d.setColor(Color.CYAN);
		g2d.drawString("life: " + hero.getLife(), 0, 20);
		g2d.drawString("score: ", 300, 20);
		
		g.drawImage(image, 0, 0, null);
	}
}
