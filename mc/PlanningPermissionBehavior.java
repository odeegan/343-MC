package mc;

public class PlanningPermissionBehavior implements SquareBehavior {

	public PlanningPermissionBehavior() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		GamePane.getInstance().setMessagePanelText("You landed on Planning Permission");
		GamePane.getInstance().addSelectionLayer();


	}

}
