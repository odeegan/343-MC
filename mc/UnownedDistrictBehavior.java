package mc;

public class UnownedDistrictBehavior implements SquareBehavior{

	public UnownedDistrictBehavior() {}
	
	public void execute() {
		GamePane.getInstance().setMessagePanelText("You landed on an Unowned District");
	}
}
