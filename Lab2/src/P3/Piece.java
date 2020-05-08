package P3;

public class Piece {
	private final String Name;
	private final Player belong;
	//private final Integer Quantity;
	public Piece(String name, Player numPlayer) {
		this.Name=name;
		this.belong=numPlayer;
	}
	public Piece() {
		Name=null;
		belong=null;
	}
	public String getName() {
		return this.Name;
	}
	public Player getPlayer() {
		return this.belong;
	}
}
