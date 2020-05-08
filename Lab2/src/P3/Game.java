package P3;

public interface Game {
	public static Game newGame(String name) {
		if(name.toLowerCase().equals("chess")) {
			return new ChessGame(8,8);
		}
		if(name.toLowerCase().equals("go")) {
			return new GoGame(19,19);
		}
		else {
			throw new RuntimeException("not implemented");
		}
	}
	public boolean move(Position sourcePosition, Position targetPosition);
	
	public boolean add(Position position, Piece newPiece);
	
	public boolean remove(Position position);
	
	public String show();
	
	public void playerSwitch();
	
	public boolean Repent();
	
	public Player getPlayer(int i);
}
