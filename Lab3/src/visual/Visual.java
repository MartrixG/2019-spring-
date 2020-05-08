package visual;

import circularOrbit.CircularOrbit;
import javafx.util.Pair;
import position.Position;
import track.Track;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Visual {
    CircularOrbit c;
    JFrame frame = new JFrame();
    public Visual(CircularOrbit c){
        this.c=c;
    }
    public void dispose(){
        frame.dispose();
    }
    public void show(){
        int coefficient =50;
        int centerObjectRadius = 30;
        int physicalObjectRadius = 10;
        int ScreenWidth = 1200;
        int ScreenHeight = 1200;
        JPanel panel=new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);

                g.setColor(Color.WHITE);
                g.fillRect(0, 0, ScreenWidth, ScreenHeight);
                g.setColor(Color.RED);
                g.fillOval(ScreenWidth / 2 - centerObjectRadius / 2, ScreenHeight / 2 - centerObjectRadius / 2, centerObjectRadius, centerObjectRadius);

                for (Object i:c.getTracks()) {
                    g.setColor(Color.BLACK);
                    g.drawOval(ScreenWidth / 2 - ((Track)i).getNoNumber() * coefficient, ScreenHeight / 2 - ((Track)i).getNoNumber() * coefficient,
                            ((Track)i).getNoNumber() * coefficient * 2, ((Track)i).getNoNumber() * coefficient * 2);
                    if(c.getPhysicalObjects(((Track)i))!=null) 
                    for (Object j : c.getPhysicalObjects(((Track)i))) {
                        g.setColor(Color.BLUE);
                        int xOfElectronic = (int) (ScreenWidth / 2
                                + Math.cos(c.getPhysicalObjectPosition(j).getPolarCoordinatePosition().getValue()) * ((Track)i).getNoNumber()
                                * coefficient);
                        int yOfElectronic = (int) (ScreenHeight / 2
                                + Math.sin(c.getPhysicalObjectPosition(j).getPolarCoordinatePosition().getValue()) * ((Track)i).getNoNumber()
                                * coefficient);
                        g.fillOval(xOfElectronic - physicalObjectRadius / 2, yOfElectronic - physicalObjectRadius / 2, physicalObjectRadius, physicalObjectRadius);
                    }
                }
                List<Pair<Position, Position>> line=c.getStraight();
                if(line.size()!=0){
                    for(Pair<Position, Position> eachPair:line){
                        g.setColor(Color.BLACK);
                        int x1=(int)(ScreenWidth / 2 + (eachPair.getKey().getCartesianCoordinatePosition().getKey())* coefficient);
                        int y1=(int)(ScreenHeight / 2 + (eachPair.getKey().getCartesianCoordinatePosition().getValue())* coefficient);
                        int x2=(int)(ScreenWidth / 2 + (eachPair.getValue().getCartesianCoordinatePosition().getKey())* coefficient);
                        int y2=(int)(ScreenHeight / 2 + (eachPair.getValue().getCartesianCoordinatePosition().getValue())* coefficient);
                        g.drawLine(x1,y1,x2,y2);
                    }
                }
            }
        };
        frame.setSize(1200, 1200);
        frame.add(panel);
        frame.setVisible(true);
    }
}
