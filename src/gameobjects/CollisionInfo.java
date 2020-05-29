package gameobjects;

import geometryshapes.Point;
import interfaces.Collidable;
/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-03-28.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidableObject;

    /**
     * constractur function.
     *
     * @param collisionPoint   a Point object. the collision point.
     * @param collidableObject a Collidable object. the collision object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collidableObject) {
        this.collisionPoint = collisionPoint;
        this.collidableObject = collidableObject;
    }


    /**
     * returns the collision point.
     *
     * @return a Point object. the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * returns the collision object.
     *
     * @return a Collidable object. the collision object.
     */
    public Collidable collisionObject() {
        return this.collidableObject;
    }
}