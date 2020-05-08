package P3;

public class Action {
	Board b;
	Position p;
	Piece q;
	public Action(Board b, Position p, Piece q) {
		this.b=b;
		this.p=p;
		this.q=q;
	}
	public void add() {
		if(b.B==null) {
			throw new NullPointerException();
		}else {
			b.B[p.i][p.j]=q;
		}
	}
}
