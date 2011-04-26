package mc;

public class Square {

	int position;
	SQUARETYPE type;
	SquareBehavior squareBehavior;
	
				
	public Square(SQUARETYPE type){
		this.type = type;
	}
	
	public void performBehavior() {
		squareBehavior.execute();
	}
	
	public void setSquareBehavior(SquareBehavior sb) {
		squareBehavior = sb;
	}
}