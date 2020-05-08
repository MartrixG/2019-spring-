package position;

import javafx.util.Pair;

/**
 * A immutable ADT to describe a relative position of one objects to the center object
 *
 * @author Martrix
 */
public class Position {
    /**
     * field of all the variables.
     * <abscissa> The abscissa in a Cartesian coordinate system.
     * <ordinate> The ordinate in a Cartesian coordinate system.
     * <polarity> The polarity in a polar coordinate system.
     * <polarAngle> The polar angle in a polar coordinate system.
     */
    private final Double abscissa;
    private final Double ordinate;
    private final Double polarity;
    private final Double polarAngle;

    /**
     * The class position's construct method.
     * If op == 1, means the position is constructed by a Cartesian coordinate system.
     * A means abscissa.B means ordinate
     * If op == 2, means the position is constructed by a polar coordinate system.
     * A means polarity.B means polar angle
     *
     * @param a  abscissa or polarity
     * @param b  ordinate or polar angle
     * @param op Decide the function of this construct method.Can only be 1 or 2.
     */
    public Position(Double a, Double b, int op) {
        if (op == 1) {
            //A - x  B - y
            this.abscissa = a;
            this.ordinate = b;
            this.polarity = Math.sqrt(a * a + b * b);
            this.polarAngle = Math.atan2(b, a);
        } else if (op == 2) {
            //A - r  B - theta
            this.polarity = a;
            this.polarAngle = b;
            this.abscissa = a * Math.cos(b);
            this.ordinate = a * Math.sin(b);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Get the position's abscissa and ordinate in Cartesian coordinate.
     *
     * @return a pair of (x,y)
     */
    public Pair<Double, Double> getCartesianCoordinatePosition() {
        Double x = new Double(abscissa.doubleValue());
        Double y = new Double(ordinate.doubleValue());
        return new Pair<>(x, y);
    }

    /**
     * Get the position's polarity and polar angle in polar coordinate.
     *
     * @return a pair of (r,θ)
     */
    public Pair<Double, Double> getPolarCoordinatePosition() {
        Double r = new Double(polarity.doubleValue());
        Double theta = new Double(polarAngle.doubleValue());
        return new Pair<>(r, theta);
    }
}
