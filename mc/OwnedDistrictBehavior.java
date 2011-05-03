package mc;

public class OwnedDistrictBehavior implements SquareBehavior {

	@Override
	public void execute() {
		GamePane.getInstance().setMessagePanelText("You landed on an Owned District");

	}

}
