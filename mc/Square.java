package mc;

public class Square {

	int position;
	SQUARETYPE type;
	SquareBehavior squareBehavior;
	
				
	public Square(){
		// empty constructor
	}
	
	public void performBehavior() {
		squareBehavior.execute();
	}
	
	public void setSquareBehavior(SquareBehavior sb) {
		squareBehavior = sb;
	}
}