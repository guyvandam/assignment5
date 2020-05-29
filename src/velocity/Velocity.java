package velocity;

import geometryshapes.Point;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 2.0
 * @since 2020-03-28.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor function.
     *
     * @param dx a double. the value of the dx field.
     * @param dy a double. the value of the dy field.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * a static function. get an angle and a speed (the size of the speed) and returns a velocity matching to the input
     * value.
     * <p>
     * calculates dx and dy using a "polaric" representation. (x,y) = (r*cos(theta),r*sin(theta)). calculates the
     * input angle in radians to match the input of the sin and cos functions of the Math class.
     * </p>
     * <p>
     * note: the sin function returns a very small number instead of 0 when needed. probably due to a numerical reason.
     *
     * @param angle a double. the angle of the wanted velocity.
     * @param speed a double. the size of the wanted velocity.
     * @return a GameObjects.Velocity object. the velocity matching to the input values.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos((angle * Math.PI) / 180);
        double dy = speed * Math.sin((angle * Math.PI) / 180);
        return new Velocity(dx, dy);
    }

    /**
     * applies the dx and dy values to the x and y values [respectively] of the input point. and returns a new point
     * with those values.
     * <p>
     * further explanation:
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy)
     * </p>
     *
     * @param p a GeometryShapes.Point object. the point we want to change the position of.
     * @return a GeometryShapes.Point object. the point with new x and y value after the addition of the dx and dy.
     */
    public Point applyToPoint(Point p) {
        if (p != null) {
            return new Point(p.getX() + this.dx, p.getY() + this.dy);
        }
        return null;
    }

    /**
     * @return a double. the value of the dx field.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return a double. the value of the dy field.
     */
    public double getDy() {
        return this.dy;
    }
}