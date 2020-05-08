package P3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyChessAndGoGame {
	public static void main(String argc[]) throws IOException {
		System.out.println("Please input the name of game.");
		BufferedReader buffReader = null;
		try {
			buffReader = new BufferedReader(new InputStreamReader(System.in));
			Game chGame=null;
			String op=buffReader.readLine();
			if(op.equals("chess")) {
				chGame=Game.newGame("chess");
				System.out.print(chGame.show());
				while(true) {
					op=buffReader.readLine();
					if(op.equals("end")) {
						break;
					}
					else if(op.charAt(0)=='1') {
						Position sPosition=new Position(((int)op.charAt(2))-48,((int)op.charAt(4))-48);
						Position tPosition=new Position(((int)op.charAt(6))-48,((int)op.charAt(8))-48);
						chGame.move(sPosition, tPosition);
						chGame.playerSwitch();
						System.out.print(chGame.show());
					}
					else if(op.charAt(0)=='2') {
						chGame.Repent();
						chGame.playerSwitch();
						System.out.print(chGame.show());
					}
					else {
						System.out.print("Illeagle operation.");
					}
				}
			}
			if(op.equals("go")) {
				chGame=Game.newGame("go");
				System.out.print(chGame.show());
				int sum=1;
				while(true) {
					op=buffReader.readLine();
					if(op.equals("end")) {
						break;
					}
					if(op.charAt(0)=='1') {
						Position sPosition=new Position(((int)op.charAt(2))-48,((int)op.charAt(4))-48);
						if(sum%2==1) {
							chGame.add(sPosition, chGame.getPlayer(1).pieceList.get(0));
							sum++;
						}else {
							chGame.add(sPosition, chGame.getPlayer(2).pieceList.get(0));
							sum++;
						}
						chGame.playerSwitch();
						System.out.print(chGame.show());
					}else if(op.charAt(0)=='2') {
						chGame.Repent();
						System.out.print(chGame.show());
						sum--;
					}else if(op.charAt(0)=='3') {
						Position sPosition=new Position(((int)op.charAt(2))-48,((int)op.charAt(4))-48);
						chGame.remove(sPosition);
						System.out.print(chGame.show());
					}
				}
			}
		}finally {
			if(buffReader!=null) {
				try {
					buffReader.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
				buffReader=null;
			}
		}
	}
}
