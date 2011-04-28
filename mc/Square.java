package mc;

public class Square {

	int position;
	SQUARETYPE type;
	String name;
	SquareBehavior squareBehavior;
	
	public Square(){
	}
	
	public Square(SQUARETYPE type){
		this.type = type;
	}
	
	public SQUARETYPE getType() {
		return type;
	}
	
	public String getName() {
		if(type == SQUARETYPE.DISTRICT) {
			return name;
		} else {
			return type.getName();
		}
	}
	
	public void performBehavior() {
		squareBehavior.execute();
	}
	
	public void setSquareBehavior(SquareBehavior sb) {
		squareBehavior = sb;
	}
}