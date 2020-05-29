package geometryshapes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-03-28.
 */

public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * constructor function.
     *
     * @param upperLeft a GeometryShapes.Point object.
     * @param width     a double.
     * @param height    a double.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a (possibly empty) List of intersection points with the input line.
     * <p>
     * finds the closest intersection to the start of the line (if exist), 'flips' the line so the
     * 'closestIntersectionToStartOfLine' will returns the other intersection point (if exist).
     * </p>
     *
     * @param line a GeometryShapes.Line object.
     * @return a java-ArrayList object. null if there's no intersection points.
     */
    //
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        if (line == null) {
            return null;
        }

        List<Point> returnPoints = new ArrayList<>();
        Line[] lineList = {line, new Line(line.end(), line.start())};
        for (Line l : lineList) {
            Point temp = l.closestIntersectionToStartOfLine(new Rectangle(this.upperLeft, this.width, this.height));
            if (temp != null && !returnPoints.contains(temp)) {
                returnPoints.add(temp);

            }
        }

        if (returnPoints.isEmpty()) {
            return null;
        } else {
            return returnPoints;
        }


    }

    /**
     * @return a double. the width of the GeometryShapes.Rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return a double. the height of the GeometryShapes.Rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return a GeometryShapes.Point object. the upper left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * sets the input GeometryShapes.Point to be the upperLeft point.
     *
     * @param newUpperLeft a GeometryShapes.Point object.
     */
    public void setUpperLeft(Point newUpperLeft) {
        this.upperLeft = newUpperLeft;
    }

    /**
     * @return a GeometryShapes.Point object. the upper right corner point.
     */
    public Point getUpperRight() {
        return new Point(this.getUpperLeft().getX() + getWidth(), this.getUpperLeft().getY());
    }

    /**
     * @return a GeometryShapes.Point object. the lower left corner points.
     */
    public Point getLowerLeft() {
        return new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + getHeight());
    }

    /**
     * @return a GeometryShapes.Point object. the lower right corner points.
     */
    public Point getLowerRight() {
        return new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY() + this.getHeight());
    }

    /**
     * @return a GeometryShapes.Line object. the upper edge of the rectangle
     */
    public Line getUpperEdge() {
        return new Line(this.getUpperLeft(), this.getUpperRight());
    }

    /**
     * @return a GeometryShapes.Line object. the lower edge of the rectangle.
     */
    public Line getLowerEdge() {
        return new Line(this.getLowerLeft(), this.getLowerRight());
    }

    /**
     * @return a GeometryShapes.Line object. the left edge of the rectangle
     */
    public Line getLeftEdge() {
        return new Line(this.getUpperLeft(), this.getLowerLeft());
    }


    /**
     * @return a GeometryShapes.Line object. the right edge of the rectangle
     */

    public Line getRightEdge() {
        return new Line(this.getUpperRight(), this.getLowerRight());
    }

    /**
     * checks if the input point is in the vertical bounds of the rectangle.
     *
     * @param p a point object.
     * @return a boolean. true if the input line is in the vertical bounds of the rectangle, false otherwise.
     */
    public boolean isInVerticalBounds(Point p) {
        return p != null && this.getUpperLeft().getX() < p.getX() && p.getX() < this.getUpperRight().getX();
    }

    /**
     * checks if the input point is in the horizontal bounds of the rectangle.
     *
     * @param p a point object.
     * @return a boolean. true if the input line is in the horizontal bounds of the rectangle, false otherwise.
     */
    public boolean isInHorizontalBounds(Point p) {
        return p != null && this.getUpperLeft().getY() < p.getY() && p.getY() < this.getLowerLeft().getY();
    }

    /**
     * checks if the input point is in the rectangle, i.e. in its vertical and horizontal.
     * <p>
     *
     * @param p a point object.
     * @return a boolean. true if the input point is in the rectangle, false otherwise.
     */
    public boolean isInRectangle(Point p) {
        return p != null && isInHorizontalBounds(p) && isInVerticalBounds(p);
    }
}