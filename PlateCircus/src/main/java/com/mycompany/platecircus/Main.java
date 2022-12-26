package com.mycompany.platecircus;
import com.mycompany.platecircus.world.GameWorld;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Uncomment any of the lines in the Main to run a new game, Have Fun :)");

		/* -------------------------------------------------------------------- */
		/* using default background color */
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.Gold(400, 400));
		
		/* -------------------------------------------------------------------- */
		/* using background color at the game */
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.Ball(700, 400), Color.YELLOW);
		
		/* -------------------------------------------------------------------- */
		/* controlling the behavior of the close button
		 * alternatively, you can use JFrame.DISPOSE_ON_CLOSE, JFrame.HIDE_ON_CLOSE */
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.Bubles(600, 600), JFrame.EXIT_ON_CLOSE);

//		/* using menus in the game */
//		JMenuBar  menuBar = new JMenuBar();;
//		JMenu menu = new JMenu("File");
//		JMenuItem  exitMenuItem = new JMenuItem("Exit");
//		exitMenuItem.addActionListener(new ActionListener() {
//			@Override public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//		});
//		menu.add(exitMenuItem);
//		menuBar.add(menu);
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.StarWar(800, 600), menuBar, Color.BLACK);

		/* -------------------------------------------------------------------- */
		/* allow pause, resume, and restart multiple worlds in the same frame */
		JMenuBar  menuBar = new JMenuBar();;
		JMenu menu = new JMenu("File");
		JMenuItem newEasyMenuItem = new JMenuItem("New Easy");
                JMenuItem newMedMenuItem = new JMenuItem("New Medium");
                JMenuItem newHardMenuItem = new JMenuItem("New Hard");
		JMenuItem pauseMenuItem = new JMenuItem("Pause");
		JMenuItem resumeMenuItem = new JMenuItem("Resume");
		menu.add(newEasyMenuItem);
                menu.add(newMedMenuItem);
                menu.add(newHardMenuItem);
		menu.addSeparator();
		menu.add(pauseMenuItem);
		menu.add(resumeMenuItem);
		menuBar.add(menu);
		final GameController gameController = GameEngine.start("Testing Version", GameWorld.getWorld(), menuBar, Color.WHITE);
		newEasyMenuItem.addActionListener(new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
                            GameWorld.resetWorld("easy");
				gameController.changeWorld(GameWorld.getWorld());
			}
		});
                newMedMenuItem.addActionListener(new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
				GameWorld.resetWorld("medium");
				gameController.changeWorld(GameWorld.getWorld());
			}
		});
                newHardMenuItem.addActionListener(new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
				GameWorld.resetWorld("hard");
				gameController.changeWorld(GameWorld.getWorld());
			}
		});
		pauseMenuItem.addActionListener(new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
				gameController.pause();
			}
		});
		resumeMenuItem.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				gameController.resume();
			}
		});
	}
	
}
