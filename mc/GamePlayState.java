package mc;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePlayState implements GameState {
	GameStateMachine gameStateMachine;
	
	JFrame mainFrame;
	JComponent layeredPane;

	
	public GamePlayState(GameStateMachine gsm, JFrame mf) {
		gameStateMachine = gsm;
		mainFrame = mf;
		
		layeredPane = new JLayeredPane();
		layeredPane.setOpaque(true);
		
		ImageIcon icon = new ImageIcon("images/board-small.png");
		JLabel background = new JLabel(icon);
		background.setOpaque(true);
		background.setBounds(0,0, 1100, 768);
		
		layeredPane.add(background, new Integer(0), 1);
	}

	
	public void enter() {};
	
	public void enter(int numPlayers) {
		System.out.println("This is the GamePlayState");
		mainFrame.setContentPane(layeredPane);
		layeredPane.revalidate();
		layeredPane.repaint();
		
		
		
	}

	
}