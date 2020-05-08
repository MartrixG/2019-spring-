package visual;

import ladder.Ladder;
import ladder.RunningLadder;
import monkey.Monkey;
import runs.CrossRiver;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Visual extends Thread {
    public static JFrame frame = new JFrame();

    @Override
    public void run() {
        while (CrossRiver.go) {
            //frame.repaint();
            //show();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void show() {
        int screenWidth = 1600;
        int screenHeight = 900;
        int startOfLeftX = 20;
        int startOfLeftY = 20;
        int dOfCircle = 10;
        int numOfWide = 5;
        int lineStartX = 120;
        int lineStartY = 25;
        int lineEndX = 870;
        int lineEndY = 25;
        JPanel panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);

                g.setColor(Color.WHITE);
                g.fillRect(0, 0, screenWidth, screenHeight);

                g.setColor(Color.BLUE);
                int nowNum = -1;
                synchronized (CrossRiver.leftMonkey) {
                    for (Monkey eachM : CrossRiver.leftMonkey) {
                        if (eachM.getDirection() == 0) {
                            nowNum++;
                            int x = startOfLeftX + (nowNum % numOfWide - 1) * 2 * dOfCircle;
                            int y = startOfLeftY + (nowNum / numOfWide) * 2 * dOfCircle;
                            g.fillOval(x, y, dOfCircle, dOfCircle);
                        }
                    }
                }

                g.setColor(Color.BLACK);
                int numOfLine = CrossRiver.runningLadders.keySet().size();
                for (int i = 0; i < 2 * numOfLine * dOfCircle; i += 2 * dOfCircle) {
                    g.drawLine(lineStartX, lineStartY + i, lineEndX, lineEndY + i);
                }

                g.setColor(Color.RED);
                nowNum = -1;
                synchronized (CrossRiver.leftMonkey) {
                    for (Monkey eachM : CrossRiver.leftMonkey) {
                        if (eachM.getDirection() == 1) {
                            nowNum++;
                            int x = startOfLeftX + 890 + (nowNum % numOfWide - 1) * 2 * dOfCircle;
                            int y = startOfLeftY + (nowNum / numOfWide) * 2 * dOfCircle;
                            g.fillOval(x, y, dOfCircle, dOfCircle);
                        }
                    }
                }

                int i = 0;
                for (Map.Entry<Ladder, RunningLadder> eachE : CrossRiver.runningLadders.entrySet()) {
                    int x = lineStartX;
                    int y = lineStartY + i;
                    if (CrossRiver.directionOfLadder.get(eachE.getKey()) == 0) {
                        g.setColor(Color.BLUE);
                    } else {
                        g.setColor(Color.RED);
                    }
                    for (int j = 1; j <= eachE.getKey().getH(); j++) {
                        if (eachE.getValue().getPos()[j] == 1) {
                            g.fillOval(x + (j - 1) * (750 / eachE.getKey().getH() + 2), y - dOfCircle / 2, dOfCircle, dOfCircle);
                        }
                    }
                    //g.drawLine(lineStartX, lineStartY + i, lineEndX, lineEndY + i);
                    i += dOfCircle * 2;
                }
            }
        };
        frame.setSize(1600, 900);
        frame.add(panel);
        frame.setVisible(true);
    }

}
