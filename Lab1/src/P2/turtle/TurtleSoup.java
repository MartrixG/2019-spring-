/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Collections;
import P2.turtle.Vec;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	for(int i=1;i<=4;i++) {
    		turtle.forward(sideLength);
    		turtle.turn(90.0);
    	}
        //throw new RuntimeException("implement me!");
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	double angle;
    	angle=180.0-360.0/sides;
    	return angle;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        int side;
        side=(int) Math.round(360.0/(180.0-angle));
        return side;
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        double angle;
        angle=calculateRegularPolygonAngle(sides);
    	for(int i=1;i<=sides;i++) {
    		turtle.forward(sideLength);
    		turtle.turn(180-angle);
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	double x1,y1,x2,y2;
    	x1=Math.sin(Math.toRadians(currentBearing));
    	y1=Math.cos(Math.toRadians(currentBearing));
    	x2=targetX-currentX;
    	y2=targetY-currentY;
    	double S=x1*y2-x2*y1;
    	double theta=Math.asin((x1*y2-x2*y1)/Math.sqrt(x2*x2+y2*y2));
    	theta=Math.toDegrees(theta);
    	if(S==0) {
    		if((x1*x2+y1*y2)>0) theta=0;
    		else theta=180;
    	}
    	else if(S<0) {
    		theta=-theta;
    		if((x1*y2+x2*y1)<0) {
    			theta=180-theta;
    		}
    	}
    	else if(S>0){
    		if(x1*y2+x2*y1<0) {
    			theta=180-theta;
    		}
    		theta=360-theta;
    	}
    	System.out.printf("%.6f",theta);
        return theta;
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
    	int listSize;
    	Double currentBearing=(double) 0;
    	List<Double> bearingToPoint=new ArrayList<Double>();
    	listSize=xCoords.size();
    	for(int i=0;i<listSize-1;i++) {
    		int currentX,currentY,targetX,targetY;
    		currentX=xCoords.get(i);
    		currentY=yCoords.get(i);
    		targetX=xCoords.get(i+1);
    		targetY=yCoords.get(i+1);
    		bearingToPoint.add(calculateBearingToPoint(currentBearing,currentX,currentY,targetX,targetY));
    		currentBearing+=bearingToPoint.get(i);
    		currentBearing%=360;
    	}
    	return bearingToPoint;
    	//throw new RuntimeException("implement me!");
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    
    public static Set<Point> convexHull(Set<Point> points) {
        Set<Point> anserPoints = new HashSet<Point>();
        List<Point> inPoints=new ArrayList<Point>();
        if(points.size()<=2) {
        	return points;
        }
        for(Point x:points) {
        	inPoints.add(x);
        }
        Collections.sort(inPoints, new Comparator<Point>() {
        	public int compare(Point A,Point B) {
        		if(A.x()<B.x()) {
        			return -1;
        		}else if(A.x()>B.x()) {
        			return 1;
        		}else if(A.y()<B.y()) {
        			return -1;
        		}else if(A.y()>B.y()) {
        			return 1;	
        		}
        		return 1;
        	}
        });
        Point stackPoints[]=new Point[inPoints.size()+1];
        int top=0;
        Vec v1 = new Vec();
        Vec v2 = new Vec();
        for(int i=0;i<inPoints.size();i++) {
        	while(true) {
        		if(top<2) break;
        		v1.pointToVec(stackPoints[top-2], inPoints.get(i));
        		v2.pointToVec(stackPoints[top-2], stackPoints[top-1]);
        		if(v1.Cross(v2)>=0) {
        			top--;
        		}else {
        			break;
        		}
        	}
        	stackPoints[top++]=inPoints.get(i);
        }
        int k=top;
        for(int i=inPoints.size()-2;i>=0;i--) {
        	while(true) {
        		if(top<=k) break;
        		v1.pointToVec(stackPoints[top-2], inPoints.get(i));
        		v2.pointToVec(stackPoints[top-2], stackPoints[top-1]);
        		if(v1.Cross(v2)>=0) {
        			top--;
        		}else {
        			break;
        		}
        	}
        	stackPoints[top++]=inPoints.get(i);
        }
        top--;
        for(int i=1;i<=top;i++) {
        	anserPoints.add(stackPoints[i]);
        }
        return anserPoints;
    	//throw new RuntimeException("implement me!");
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        turtle.turn(90);
    	for(int i=1;i<=5;i++) {
        	turtle.forward(80);
        	turtle.turn(144);
        }
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        drawSquare(turtle, 40);
        //drawRegularPolygon(turtle,5,40);
        //calculateBearingToPoint(0,0,0,0,1);
        //drawPersonalArt(turtle);
        // draw the window
        turtle.draw();
    }

}
