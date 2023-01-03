package com.mycompany.platecircus;
import com.mycompany.platecircus.world.GameWorld;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eg.edu.alexu.csd.oop.game.GameEngine;

public class Main {
	
	public static void main(String[] args) {
		
                GameController gameController = new GameController(() -> GameWorld.getWorld());
                gameController.start();
	}
	
}
