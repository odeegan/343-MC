package mc;

public class Square {

	int position;
	SQUARETYPE type;
	SquareBehavior squareBehavior;
	
	int xx;
	int yy;
	
	public Square(){
	}
	
	public Square(SQUARETYPE type){
		this.type = type;
	}
	
	public SQUARETYPE getType() {
		return type;
	}
	
	public String getName() {
		return type.getName();
	}
	
	public void performBehavior() {
		squareBehavior.execute();
	}
	
	public void setSquareBehavior(SquareBehavior sb) {
		squareBehavior = sb;
	}
	
	public void setX(int xx) {
		this.xx = xx;
	}
	
	public void setY(int yy) {
		this.yy = yy;
	}
	
	public int getX() {
		return xx;
	}
	
	public int getY() {
		return yy;
	}
	
	/*
	 * Override the standard toString message.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {		
		return getName();
	}
	
}