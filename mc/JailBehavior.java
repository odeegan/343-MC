package mc;

public class JailBehavior implements SquareBehavior {

	public JailBehavior() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		GamePane.getInstance().setMessagePanelText("You're visiting Jail");

	}

}
