package P3;

import java.util.ArrayList;
import java.util.List;

public class GoGame implements Game {
	Board gameBoard;
	Piece blankPiece=new Piece("＋", new ChessPlayer(0, "admin"));
	public GoPlayer Player1=new GoPlayer(1, "Player1");
	public GoPlayer Player2=new GoPlayer(2, "Player2");
	List<Action> Actions=new ArrayList<Action>();
	public GoGame(int l,int w) {
		gameBoard=new Board(l, w);
		for(int i=1;i<=l;i++) {
			for(int j=1;j<=w;j++) {
				Position putPosition=new Position(i, j);
				Action nowAction;
				nowAction=new Action(gameBoard, putPosition, blankPiece);
				nowAction.add();
			}
		}
	}
	@Override
	public boolean move(Position sourcePosition, Position targetPosition) {
		return false;
	}
	@Override
	public boolean add(Position position, Piece newPiece) {
		if(!newPiece.getPlayer().getGo()) {
			System.out.print(newPiece.getName());
			System.out.println(" can not put the piece.");
		}
		if(!gameBoard.getPiece(position).equals(blankPiece)) {
			System.out.println("This area exit a piece.");
			return false;
		}
		Action nowAction=new Action(gameBoard, position, newPiece);
		Action preAction=new Action(gameBoard, position, blankPiece);
		nowAction.add();
		Actions.add(preAction);
		return false;
	}
	@Override
	public boolean remove(Position position) {
		if(gameBoard.getPiece(position).equals(" ")) {
			System.out.println("Can not remove this aera.");
			return false;
		}
		Action nowAction=new Action(gameBoard, position, blankPiece);
		Action reAction=new Action(gameBoard, position, gameBoard.getPiece(position));
		nowAction.add();
		Actions.add(reAction);
		return false;
	}
	@Override
	public String show() {
		String c="ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳ";
		String showString=new String();
		showString="  Ａ Ｂ Ｃ Ｄ Ｅ Ｆ Ｇ Ｈ Ｉ Ｊ Ｋ Ｌ Ｍ Ｎ Ｏ Ｐ Ｑ Ｒ Ｓ\n";
		for(int i=1;i<=19;i++) {
			showString+=c.charAt(Integer.valueOf(i)-1);
			for(int j=1;j<=19;j++) {
				showString+=gameBoard.getPiece(new Position(i,j)).getName();
				showString+=" ";
			}
			showString+='\n';
		}
		return showString;
	}
	@Override
	public void playerSwitch() {
		Player1.Switch();
		Player2.Switch();
	}
	@Override
	public boolean Repent() {
		if(Actions.size()<2) {
			System.out.println("No more history record.");
			return false;
		}
		Actions.get(Actions.size()-1).add();
		Actions.remove(Actions.size()-1);
		return true;
	}
	@Override
	public GoPlayer getPlayer(int i) {
		if(i==1) {
			return Player1;
		}
		else{
			return Player2;
		}
	}
}
