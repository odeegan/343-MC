package mc;

import java.awt.Color;

import javax.swing.JFrame;

public class GameStateMachine {

	GameMaster gameMaster;
	GameState mainMenuState;
	GameState gamePlayState;
	GameState state;

	JFrame mainFrame;

	public GameStateMachine() {
		// create and set the dimensions of the main window
		buildMainWindow();
		// passing the mainFrame to each GameState and controller class 
		// (i.e. GameMaster) allows them to draw to the screen
		mainMenuState = new MainMenuState(this, mainFrame);
		gamePlayState = new GamePlayState(this, mainFrame);
		System.out.println("Starting the GameStateMachine.");
	}

	public void init() {
		setState(mainMenuState);
		
	}
	
	public void setState(GameState newState) {
		state = newState;
		state.enter();
	}
		
	public GameState getGamePlayState() {
		return gamePlayState;
	}
	
	public GameState getMainMenuState() {
		return mainMenuState;
	}
	
	/*
	 * Creates and sets the dimenisions of the main game window
	 */
	private void buildMainWindow() {
		mainFrame = new JFrame("Monopoly City");
		mainFrame.setSize(1200, 800);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
