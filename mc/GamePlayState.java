package mc;

import java.awt.*;
import javax.swing.*;



public class GamePlayState implements GameState {
	
	GameStateMachine gameStateMachine;
	GameMaster gameMaster;
	
	JFrame mainFrame;
	GamePane layeredPane;
	
	public GamePlayState(GameStateMachine gsm, GameMaster gm, JFrame mf) {
		gameStateMachine = gsm;
		gameMaster = gm;
		mainFrame = mf;
		
		layeredPane = GamePane.getInstance();
	}
		
		
	public void enter() {
		System.out.println("This is the GamePlayState");
		mainFrame.setContentPane(layeredPane);
		layeredPane.revalidate();
		gameMaster.enterGameLoop();
	}
}