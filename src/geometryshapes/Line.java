package geometryshapes;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 2.0
 * @since 2020-03-28.
 */


public class Line {
    private Point start;
    private Point end;

    /**
     * constructor function
     * <p>
     * initials the start and end fields.
     * </p>
     *
     * @param start a point. the value for the start field
     * @param end   a point. the value for the end field
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor function
     * <p>
     * initials the start and end fields. gets the x and y values for each point instead of the points themselves.
     * </p>
     *
     * @param x1 a double. the value for the start.x field
     * @param y1 a double. the value for the start.y field
     * @param x2 a double. the value for the end.x field
     * @param y2 a double. the value for the end.y field
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * calculates the length of the line
     * <p>
     * returns the distance between the start and end point, the length of the distance.
     * </p>
     *
     * @return a double. the length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * calculates and return the middle point of the line.
     * <p>
     * the middle point x and y values are the average between the x and y values of the end and start points.
     * </p>
     *
     * @return a point. the middle point of the line.
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * returns the start point of the line.
     *
     * @return a point. the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * returns the end point of the line.
     *
     * @return a point. the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * checks if our line and the other line intersect.
     * <p>
     * uses the intersectionWith function as an help function, if the said function returns null out function will
     * return false, if not returns true.
     * </p>
     *
     * @param other a line. the line we want to check for an intersection with our line.
     * @return a boolean. true if the line intersect, false otherwise.
     */

    public boolean isIntersecting(Line other) {
        return (other != null) && !(intersectionWith(other) == null);
    }

    /**
     * checks if a point is in a line. (a line is a set of points)
     * <p>
     * uses pythagorean theorem, if the sum of distances of the input point from the edges equals to the length of the
     * line, the point is in the line.
     * </p>
     *
     * @param p a GeometryShapes.Point object.
     * @return a boolean. true if the input point is in the line. false otherwise.
     */
    public boolean isInLine(Point p) {
        return (p != null) && (Math.abs(this.start.distance(p) + this.end.distance(p) - this.length()) <= 1e-10);
    }

    /**
     * checks if our line is a vertical line.
     *
     * @return a boolean. true if the line is a vertical line. false otherwise.
     */
    public boolean isVertical() {
        return this.start.getX() == this.end.getX();
    }

    /**
     * finds the intersection point of 2 lines who are both on the same line equation.
     *
     * @param other a GeometryShapes.Line object.
     * @return a GeometryShapes.Point object. the intersection point between our line and the input line if it exist.
     * null otherwise.
     */
    public Point intersectionInEdges(Line other) {
        if (other == null) {
            return null;
        }

        if (this.start.equals(other.start()) || this.end.equals(other.start())) {
            return other.start();
        } else if (this.start.equals(other.end()) || this.end.equals(other.end())) {
            return other.end();
        } else {
            return null;
        }
    }

    /**
     * returns the intersection point of our vertical line with the input line.
     * <p>
     * checks if the input line is vertical. if so checks it to be a sub line, parallel line or a different part of the
     * line x = a. if the input line isn't vertical, does the same regular operation seen in the 'intersectionWith'
     * function, assumes an intersection point exist and checks if it true.
     * </p>
     *
     * @param other a GeometryShapes.Line object.
     * @return a GeometryShapes.Point object. the intersection point between our line and the input line. or null if it
     * doesn't exist.
     */
    public Point intersectionWithVerticalLine(Line other) {
        if (other == null) {
            return null;
        }

        if (other.isVertical()) {
            if (this.start.getX() != other.start.getX()) {
                return null;
            } else if (isInLine(other.start()) || isInLine(other.end())) { // intersection with more than 1 point.
                return null;
            } else if (!isInLine(other.start()) && !isInLine(other.end())) { //not touching.
                return null;
            } else {
                return intersectionInEdges(other);
            }
        } else {


            double m = (other.end().getY() - other.start().getY()) / (other.end().getX() - other.start().getX());
            double b = other.start().getY() - m * other.start().getX();

            double x = this.start.getX();

            Point intersectionPoint = new Point(x, m * x + b);
            if (isInLine(intersectionPoint) && other.isInLine(intersectionPoint)) {
                return intersectionPoint;
            } else {
                return null;
            }
        }
    }

    /**
     * returns the intersection point of our line and the other line.
     * <p>
     * assumes an intersection point exist, calculates the x value of the intersection point by compering the line
     * equations of our line the other line. if the y value is the same for both the line equations, then the point
     * is an intersection point, if not the function returns null.
     * then the functions checks if the point is in out line segment, it does that with the triangle inequality, if
     * the point is really on our line segment, the sum of the distances between the point and the edges should
     * equal to the distance between the edges. if so the function returns the point, if not, returns null.
     * </p>
     *
     * @param other a line. the line we want to find the intersection point with our point.
     * @return a point or a null. the intersection point (if exist, and if not a null).
     */
    public Point intersectionWith(Line other) {

        if (equals(other)) {
            return null;
        }

        if (isVertical()) {
            return intersectionWithVerticalLine(other);
        }
        if (other.isVertical()) {
            Line self = new Line(this.start, this.end);
            return other.intersectionWithVerticalLine(self);
        }


        // coefficient of our line equation.
        double m1 = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());

        //coefficient of the other line equation.
        double m2 = (other.end().getY() - other.start().getY()) / (other.end().getX() - other.start().getX());
        // b value of the y=mx+b line equation of our line.
        double b1 = this.start.getY() - m1 * this.start.getX();
        // b value the of y=mx+b line equation of the other line.
        double b2 = other.start().getY() - m2 * other.start().getX();

        if (m1 == m2) {
            if (b1 != b2) {
                return null; // two parallel line that won't touch each other.
            } else {
                return intersectionInEdges(other);
            }
        }

        double x;

        x = (b2 - b1) / (m1 - m2); // value of the x field in the intersection point.

        // point doesn't exist.
        double y1 = m1 * x + b1;
        double y2 = m2 * x + b2;

        if (Math.abs(y1 - y2) > 1e-10) {
            return null;
        }
        double y = m1 * x + b1;

        Point intersectionPoint = new Point(x, y);
        // checks if the intersection point is in the line segment.
        if (isInLine(intersectionPoint) && other.isInLine(intersectionPoint)) {
            return intersectionPoint;
        } else {
            return null;
        }
    }

    /**
     * checks if the lines are equal.
     * <p>
     * checks if the start and end points are equal, if so, returns true, if not, returns false.
     * </p>
     *
     * @param other a line. the line we check to equal to our line.
     * @return a boolean. true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return other != null && ((start.equals(other.start()) && end.equals(other.end()))
                || (start.equals(other.end()) && end.equals(other.start)));
    }

    /**
     * returns the closest intersection with the input GeometryShapes.Rectangle to the start of the line.
     * <p>
     * finds the 2 intersection points of the line with the input GeometryShapes.Rectangle. if there's just one
     * intersection point returns it, otherwise finds the point with the minimum distance to the start of the line.
     * </p>
     *
     * @param rect a GeometryShapes.Rectangle object.
     * @return GeometryShapes.Point object (or a null if an intersection point doesn't exist).
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect == null) {
            return null;
        }

        Line left = rect.getLeftEdge();
        Line right = rect.getRightEdge();
        Line upper = rect.getUpperEdge();
        Line lower = rect.getLowerEdge();

        Line[] rectangleLines = {left, right, lower, upper};
        Point p1 = null, p2 = null, temp;
        //finds all the intersection points.
        for (Line l : rectangleLines) {
            temp = this.intersectionWith(l);
            if (temp != null) {
                if (p1 == null) {
                    p1 = temp;
                } else {
                    p2 = temp;
                }
            }
        }


        if (p1 == null) {
            //no intersection points at all.
            return null;
        } else if (p2 == null) {
            //one intersection point.
            return p1;
        } else {
            //finds the closest point to the start point.
            if (this.start.distance(p1) <= this.start.distance(p2)) {
                return p1;
            } else {
                return p2;
            }
        }


    }
}