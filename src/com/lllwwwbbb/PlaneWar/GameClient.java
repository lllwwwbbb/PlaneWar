package com.lllwwwbbb.PlaneWar;

import javax.swing.JFrame;

public class GameClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2245726238010557378L;
	private static GameClient gameClient;
	Game game;
	
	public GameClient() {
		super("PlaneWar Game --- by : lllwwwbbb");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		game = new Game();
		add(game);
		game.setFocusable(true);
		game.requestFocus();
		pack();
	}
	
	public static void main(String[] args) {
		gameClient = new GameClient();
	}
}
