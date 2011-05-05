package mc;

public class ChanceBehavior implements SquareBehavior {

	public ChanceBehavior() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		GamePane.getInstance().setMessagePanelText("You landed on Chance");

	}

}
