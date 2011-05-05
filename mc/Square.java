package mc;

public class Square {

	int position;
	SQUARETYPE type;
	SquareBehavior squareBehavior;
	
	int x;
	int y;
	
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
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setXX(int xx) {
		this.xx = xx;
	}
	
	public void setYY(int yy) {
		this.yy = yy;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getXX() {
		return xx;
	}
	
	public int getYY() {
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