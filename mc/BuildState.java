package mc;

import java.awt.*;
import javax.swing.*;



public class BuildState implements GameState {
	
	GameStateMachine gameStateMachine;
	
	JFrame mainFrame;
	BuildPane layeredPane;
	
	public BuildState(GameStateMachine gsm, JFrame mf) {
		gameStateMachine = gsm;
		mainFrame = mf;
	}
		
		
	public void enter() {
		System.out.println("This is the BuildState");
		layeredPane = BuildPane.getInstance();
		mainFrame.setContentPane(layeredPane);
		layeredPane.revalidate();
		mainFrame.setVisible(true);				

	}
}