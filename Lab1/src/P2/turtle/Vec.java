package P2.turtle;
import P2.turtle.Point;

public class Vec {
	private double x;
	private double y;
	public double Cross(Vec B) {
		return this.x*B.y-B.x*this.y;
	}
	public double Dot(Vec B) {
		return this.x*B.x+this.y*B.y;
	}
	public void pointToVec(Point A,Point B) {
		this.x=B.x()-A.x();
		this.y=B.y()-A.y();
	}
}
