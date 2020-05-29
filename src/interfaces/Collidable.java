package interfaces;

import velocity.Velocity;
import geometryshapes.Ball;
import geometryshapes.Point;
import geometryshapes.Rectangle;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-03-28.
 */

public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     *
     * @return a GeometryShapes.Rectangle object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit based of the hit location on the object.
     *
     * @param collisionPoint  a GeometryShapes.Point object. the collision point.
     * @param currentVelocity a GameObjects.Velocity object. the velocity of the object hitting our Interfaces.
     *                        Collidable.
     * @param hitter          a Ball Object, the ball hitting the Collidable.
     * @return a GameObjects.Velocity object. the expected GameObjects.Velocity after the impact.
     */

    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}