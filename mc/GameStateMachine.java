package mc;

import javax.swing.JFrame;

public class GameStateMachine {

	JFrame mainFrame;
	GameState mainMenuState;
	GameState gamePlayState;
	GameState state;

	public GameStateMachine() {
		buildMainWindow();
		mainMenuState = new MainMenuState(this, mainFrame);
		gamePlayState = new GamePlayState(this, mainFrame);
		System.out.println("Starting the GameStateMachine.");
	}

	public void init() {
		setState(mainMenuState);
		
	}

	public void startGame(int numPlayers) {
		state = gamePlayState;
		state.enter(numPlayers);
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
	
	private void buildMainWindow() {
		mainFrame = new JFrame("Monopoly City");
		mainFrame.setSize(1100, 800);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
