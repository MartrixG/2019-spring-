package P3;

public class GoPlayer extends Player{
	public GoPlayer(int p, int k, int n, String name) {
		super(p, k, n, name);
	}
	public GoPlayer(int n, String name) {
		super(1, 1, n, name);
	}
	@Override
	public void Piece(int n) {
		if(n==1) {
			Piece black=new Piece("●",this);
			pieceList.add(black);
		}else {
			Piece white=new Piece("○",this);
			pieceList.add(white);
		}
	}
}
