package mc;

public class PlanningPermissionBehavior implements SquareBehavior {

	public PlanningPermissionBehavior() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		GamePane gamePane = GamePane.getInstance();
		gamePane.setMessagePanelText("You landed on Planning Permission");
		gamePane.addSelectionLayer();
		

	}

}
