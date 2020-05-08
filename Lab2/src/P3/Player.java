package P3;

import java.util.ArrayList;

public abstract class Player {
	ArrayList<Piece> pieceList=new ArrayList<Piece>();
	private  String name;
	private  Integer totPieces;
	private  Integer totKind;
	private  boolean ifGo;
	public  Integer numPlayer;
	public Player(int p, int k, int n, String name) {
		this.totPieces=p;
		this.totKind=k;
		this.numPlayer=n;
		this.name=name;
		if(n==1) {
			this.ifGo=true;
		}else {
			this.ifGo=false;
		}
		Piece(n);
	}
	public boolean getGo() {
		return this.ifGo;
	}
	public String getName() {
		return this.name;
	}
	public void Switch() {
		this.ifGo=!this.ifGo;
	}
	public abstract void Piece(int n);
}
