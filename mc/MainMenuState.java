package mc;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;

public class MainMenuState implements GameState{

	GameStateMachine gameStateMachine;
	
	JFrame mainFrame;

	Board board;
	
	public MainMenuState(GameStateMachine gsm) {
		gameStateMachine = gsm;
	}
	
	public void enter() {
		System.out.println("Entering the MainMenuState");
		
    	//mainFrame = new JFrame("Monopoly City");
    	//mainFrame.setSize(1200, 1100);
    	//mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //mainFrame.setVisible(true);
	}
	
}






