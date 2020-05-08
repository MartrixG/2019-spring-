package P3;

public class Board {
	private final Integer length;
	private final Integer wide;
	public Piece[][] B;
	public Board(int l,int w) {
		this.length=l;
		this.wide=w;
		B = new Piece[length+1][wide+1];
	}
	public Piece getPiece(Position P) {
		return B[P.i][P.j];
	}
}
