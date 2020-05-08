package P3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    void testRemove() {
        Board B=new Board(9,9);
        Piece piece=new Piece("P1",new ChessPlayer(1,new String ("P1")));
        Piece blank=new Piece();
        Position p=new Position(1,2);
        Action a1=new Action(B,p,piece);
        a1.add();
        Action a2=new Action(B,p,blank);
        a2.add();
        assertEquals(B.getPiece(p),null);
    }
    @Test
    void testadd() {
        Board B=new Board(9,9);
        Piece piece=new Piece("P1",new ChessPlayer(1,new String ("P1")));
        Position p=new Position(1,2);
        Action a1=new Action(B,p,piece);
        a1.add();
        assertEquals(B.getPiece(p),"bishop");
    }
}
