package geometryshapes;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-03-28.
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor function
     * <p>
     * initials the x and y fields.
     * </p>
     *
     * @param x a double. the value for the x field
     * @param y a double. the value for the y field
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * distance between the point another point.
     * <p>
     * uses the standard metric, square root of (x1-x2)^2 + (y1-y2)^2
     * </p>
     *
     * @param other a point. the point from it we want to measure the distance.
     * @return boolean, the distance.
     */

    public double distance(Point other) {
        return Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
                + ((this.y - other.getY()) * (this.y - other.getY())));

    }


    /**
     * checks if two points are equal
     * <p>
     * checks if the x and y values are the same as the "other" point, if so returns true, if not returns false.
     * </p>
     *
     * @param other a point. the point we compare our values with.
     * @return boolean. true if the "other" point has the same value as our point, false if not.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return this.x == other.getX() && this.y == other.getY();
    }




    /**
     * returns the value of the x field.
     *
     * @return a double. the value of the x field.
     */
    public double getX() {
        return this.x;
    }

    /**
     * returns the value of the y field.
     *
     * @return a double. the value of the y field.
     */
    public double getY() {
        return this.y;
    }
}