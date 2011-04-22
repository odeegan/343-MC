package mc;

public class GameStateMachine {

	GameState mainMenuState;
	GameState state;

	public GameStateMachine() {
		mainMenuState = new MainMenuState(this);
		System.out.println("Starting the GameStateMachine.");
	}

	public void start() {
		setState(mainMenuState);
	}

	public void setState(GameState newState) {
		state = newState;
		state.enter();
	}
	
}
