package gameobjects;

import geometryshapes.Line;
import geometryshapes.Point;
import geometryshapes.Rectangle;
import interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-03-28.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * constructor function.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * adds the input Interfaces.Collidable to the environment.
     *
     * @param c a Interfaces.Collidable object.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * removes the input Collidable object from the GameEnvironment list of Collidables.
     *
     * @param c a Collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * @return an Java-ArrayList object.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * a wrapper function for the getClosestCollision(GeometryShapes.Line trajectory, Interfaces.Collidable exception)
     * function. the same function
     * just without the exception Interfaces.Collidable. we want to check for all the items in the list.
     *
     * @param trajectory a GeometryShapes.Line object. the future path of an object that might collide with Collidables
     *                   in the list.
     * @return a CollisionInfo object. the closest collision or null if there isn't a collision at all.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        return getClosestCollision(trajectory, null);
    }

    /**
     * Assume an object moving from trajectory.start() to trajectory.end(), if this object will not collide with any of
     * the collidables in this collection (excluding the input Interfaces.Collidable - exception) , return null. Else,
     * return the
     * information about the closest collision that is going to occur.
     *
     * @param trajectory a GeometryShapes.Line object. the future path of an object that might collide with Collidables
     *                   in the list.
     * @param exception  a Interfaces.Collidable object. a Collision object we don't care for a collision with.
     * @return a CollisionInfo object. the closest collision or null if there isn't a collision at all.
     */
    public CollisionInfo getClosestCollision(Line trajectory, Collidable exception) {
        if (null == trajectory) {
            return null;
        }
        Point startOfTrajectory = trajectory.start();
        int i = 0;
        while (this.getCollidables().get(i).equals(exception)) {
            i++;
        }
        Collidable returnCollidable = this.getCollidables().get(i);
        Rectangle returnRect = returnCollidable.getCollisionRectangle();
        Point returnPoint = trajectory.closestIntersectionToStartOfLine(returnRect);

        for (Collidable c : this.getCollidables()) {
            if (c.equals(exception)) {
                continue;
            }
            Rectangle tempRect = c.getCollisionRectangle();
            Point tempPoint = trajectory.closestIntersectionToStartOfLine(tempRect);

            if ((returnPoint == null && tempPoint != null) || (returnPoint != null && tempPoint != null
                    && startOfTrajectory.distance(tempPoint) < startOfTrajectory.distance(returnPoint))) {
                returnCollidable = c;
                returnPoint = tempPoint;
            }
        }
        if (returnPoint == null) {
            return null;
        }
        return new CollisionInfo(returnPoint, returnCollidable);
    }

}