package mc;

import java.awt.*;
import javax.swing.*;



public class GamePlayState implements GameState {
	
	GameStateMachine gameStateMachine;
	
	JFrame mainFrame;
	GamePane layeredPane;
	
	public GamePlayState(GameStateMachine gsm, JFrame mf) {
		gameStateMachine = gsm;
		mainFrame = mf;
		
		layeredPane = GamePane.getInstance();
	}
		
		
	public void enter() {
		System.out.println("This is the GamePlayState");
		mainFrame.setContentPane(layeredPane);
		layeredPane.revalidate();
		// initialize the GameMaster singleton
		//GameMaster.getInstance().startTurn();
		GameMaster.getInstance().startAuction();
		GameMaster.getInstance().setGameStateMachine(gameStateMachine);
	}
}