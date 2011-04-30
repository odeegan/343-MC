package mc;

import java.awt.*;
import javax.swing.*;



public class AuctionState implements GameState {
	
	GameStateMachine gameStateMachine;
	
	JFrame mainFrame;
	JLayeredPane layeredPane;
	
	public AuctionState(GameStateMachine gsm, JFrame mf) {
		gameStateMachine = gsm;
		mainFrame = mf;
		
		layeredPane = new JLayeredPane();
		
		
		
	}
		
		
	public void enter() {
		System.out.println("This is the GamePlayState");
		mainFrame.setContentPane(layeredPane);
		layeredPane.revalidate();
		
		
	
	}
}
