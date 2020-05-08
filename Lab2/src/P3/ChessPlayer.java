package P3;

public class ChessPlayer extends Player {
	public ChessPlayer(int p, int k, int n, String name) {
		super(p, k, n, name);
	}
	public ChessPlayer(int n, String name) {
		super(16, 6, n, name);
	}
	//king queen castle 2 knight2 bishop2 pawn8 
	 @Override
	public void Piece(int n) {
		if(n==1) {//white
			Piece king = new Piece("♕", this);
			Piece queen = new Piece("♔", this);
			Piece castle = new Piece("♖", this);
			Piece knight = new Piece("♘", this);
			Piece bishop = new Piece("♗", this);
			Piece pawn = new Piece("♙", this);
			for(int i=1;i<=8;i++) {
				pieceList.add(pawn);
			}
			pieceList.add(castle);
			pieceList.add(knight);
			pieceList.add(bishop);
			pieceList.add(king);
			pieceList.add(queen);
			pieceList.add(bishop);
			pieceList.add(knight);
			pieceList.add(castle);
		}else {//black
			Piece king = new Piece("♛", this);
			Piece queen = new Piece("♚", this);
			Piece castle = new Piece("♜", this);
			Piece knight = new Piece("♞", this);
			Piece bishop = new Piece("♝", this);
			Piece pawn = new Piece("♟", this);
			pieceList.add(castle);
			pieceList.add(knight);
			pieceList.add(bishop);
			pieceList.add(king);
			pieceList.add(queen);
			pieceList.add(bishop);
			pieceList.add(knight);
			pieceList.add(castle);
			for(int i=1;i<=8;i++) {
				pieceList.add(pawn);
			}
		}
	}
}
