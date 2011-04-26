package mc;

public interface GameState {
	
	public void enter();
	
	// this second enter method allows us to pass the number of players
	// selected from the MainMenuState to the GamePlayState
	public void enter(int i);
}
