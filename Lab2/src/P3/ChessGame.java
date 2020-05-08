package P3;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.runtime.SystemProperties;

public class ChessGame implements Game{
	Board gameBoard;
	Piece blankPiece=new Piece("＋", new ChessPlayer(0, "admin"));
	ChessPlayer Player1=new ChessPlayer(1, "Player1");
	ChessPlayer Player2=new ChessPlayer(2, "Player2");
	List<Action> Actions=new ArrayList<Action>();
	public ChessGame(int l,int w) {
		gameBoard = new Board(l, w);
		int tot=0;
		for(int i=7;i<=8;i++) {
			for(int j=1;j<=8;j++) {
				Position putPosition=new Position(i, j);
				Action nowAction;
				nowAction=new Action(gameBoard, putPosition, Player1.pieceList.get(tot));
				nowAction.add();
				tot++;
			}
		}
		tot=0;
		for(int i=1;i<=2;i++) {
			for(int j=1;j<=8;j++) {
				Position putPosition=new Position(i, j);
				Action nowAction=new Action(gameBoard, putPosition, Player2.pieceList.get(tot));
				nowAction.add();
				tot++;
			}
		}
		for(int i=3;i<=6;i++) {
			for(int j=1;j<=8;j++) {
				Position putPosition=new Position(i, j);
				Action nowAction=new Action(gameBoard, putPosition, blankPiece);
				nowAction.add();
			}
		}
	}
	@Override
	public boolean move(Position sourcePosition, Position targetPosition) {
		if(gameBoard.getPiece(sourcePosition).equals(blankPiece)) {
			System.out.println("The chosen area does not exit a piece");
			return false;
		}	
		if(!gameBoard.getPiece(sourcePosition).getPlayer().getGo()) {
			System.out.print(gameBoard.getPiece(sourcePosition).getPlayer().getName());
			System.out.println(" can not move now");
			return false;
		}
		if(gameBoard.getPiece(sourcePosition).getPlayer().equals(gameBoard.getPiece(targetPosition).getPlayer())) {
			System.out.println("Can only put the pieces in a blank position");
			return false;
		}
		Piece prePiece=new Piece();
		prePiece = gameBoard.getPiece(sourcePosition);
		Action nowAction=new Action(gameBoard,sourcePosition,blankPiece);
		Action preAction=new Action(gameBoard, sourcePosition, prePiece);
		Actions.add(preAction);
		nowAction.add();
		if(gameBoard.getPiece(targetPosition).getName().equals(blankPiece)) {
			nowAction=new Action(gameBoard,targetPosition,prePiece);
			nowAction.add();
			preAction=new Action(gameBoard, targetPosition, blankPiece);
			Actions.add(preAction);
		}else {
			preAction=new Action(gameBoard, targetPosition, gameBoard.getPiece(targetPosition));
			Actions.add(preAction);
			nowAction=new Action(gameBoard,targetPosition,prePiece);
			nowAction.add();
		}
		return true;
	}
	@Override
	public boolean add(Position position, Piece newPiece) {
		Action nowAction=new Action(gameBoard,position,newPiece);
		Action preAction=new Action(gameBoard, position, blankPiece);
		nowAction.add();
		Actions.add(preAction);
		return true;
	}
	@Override
	public boolean remove(Position position) {
		Action nowAction=new Action(gameBoard,position,blankPiece);
		Action preAction=new Action(gameBoard, position, gameBoard.getPiece(position));
		nowAction.add();
		Actions.add(preAction);
		return true;
	}
	@Override
	public String show() {
		String showString=new String();
		showString=" １ ２ ３ ４ ５ ６ ７ ８\n";
		for(int i=1;i<=8;i++) {
			showString+=Integer.valueOf(i);
			for(int j=1;j<=8;j++) {
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
		Actions.get(Actions.size()-2).add();
		Actions.remove(Actions.size()-1);
		Actions.remove(Actions.size()-1);
		return true;
	}
	@Override
	public Player getPlayer(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
