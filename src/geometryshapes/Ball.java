package geometryshapes;

import gameobjects.CollisionInfo;
import gameobjects.Game;
import gameobjects.GameEnvironment;
import velocity.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 2.0
 * @since 2020-03-28.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;


    /**
     * constructor function.
     *
     * @param center a GeometryShapes.Point. the center of the ball
     * @param r      an int. the radios of the ball.
     * @param color  an java.awt.Color object. the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * constructor function.
     *
     * @param center          a GeometryShapes.Point object, the center of the ball.
     * @param r               an integer, the radios of the ball.
     * @param color           a java.awt.Color object, the color of the ball.
     * @param v               a GameObjects.Velocity object, the velocity of the ball.
     * @param gameEnvironment a GameObjects.GameEnvironment object, the game environment of the ball.
     */
    public Ball(Point center, int r, Color color, Velocity v, GameEnvironment gameEnvironment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = v;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * constructor function as well, gets x and y of the center point instead of the point itself.
     *
     * @param x     a double. the x field of the center point.
     * @param y     a double. the y field of the center point.
     * @param r     an int. the radios of the ball.
     * @param color an java.awt.Color object. the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }


    /**
     * @return a GeometryShapes.Point object, the center point of the ball.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * sets the input point to be the ball's center point.
     *
     * @param newCenter a GeometryShapes.Point object.
     */
    public void setCenter(Point newCenter) {
        this.center = newCenter;
    }

    /**
     * @return a GameObjects.GameEnvironment Object, the ball's game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    /**
     * sets the input GameObjects.GameEnvironment to be the ball's GameObjects.GameEnvironment.
     *
     * @param newGameEnvironment a GameObjects.GameEnvironment object.
     */
    public void setGameEnvironment(GameEnvironment newGameEnvironment) {
        this.gameEnvironment = newGameEnvironment;
    }

    /**
     * @return int. the x value of the center point.
     */
    public int getX() {
        return (int) (this.center.getX());
    }

    /**
     * @return int. the y value of the center point.
     */
    public int getY() {
        return (int) (this.center.getY());
    }


    /**
     * @return an int. the radios of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * @return an java.awt.Color object. the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draws the ball on the input surface. in the color of the "color" field.
     *
     * @param surface a DrawSurface object. the GUI surface we want to draw to ball on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        if (surface != null) {
            surface.setColor(this.color);
            surface.fillCircle(getX(), getY(), this.r);
        }
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * sets the velocity of the ball to the input velocity.
     *
     * @param dx a double. the dx of the desired velocity.
     * @param dy a double. the dx of the desired velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * @return a velocity object. the balls velocity.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * sets the velocity of the ball to the input velocity.
     *
     * @param velocity a velocity object. the desired velocity.
     */
    public void setVelocity(Velocity velocity) {
        if (velocity != null) {
            this.v = velocity;
        }
    }

    /**
     * moves one step in the ball's velocity. checks for the ball hitting the corners of the frame. and inverting the
     * velocity.
     * <p>
     * get the bounds of the frame. if one of the edges of the ball hits the corner it flips the sign of the dx or
     * dy, depending on the corner hit. checks for the ball going outside the frame and correct it before it happens.
     * adds the radios to calculation because we want the edges of the ball.
     * </p>
     *
     * @param leftXBound  an int. the left bound of the frame.
     * @param rightXBound an int. the right bound of the frame.
     * @param upperYBound an int. the upper bound of the frame.
     * @param lowerYBound an int. the lower bound of the frame.
     */
    public void moveOneStep(int leftXBound, int rightXBound, int upperYBound, int lowerYBound) {
        if (this.v != null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
        if (this.center.getX() - this.r + this.v.getDx() <= leftXBound || this.center.getX() + this.r + this.v.getDx()
                >= rightXBound) {
            this.setVelocity(-this.v.getDx(), this.v.getDy());

        }

        if (this.center.getY() - this.r + this.v.getDy() <= upperYBound || this.center.getY() + this.r + this.v.getDy()
                >= lowerYBound) {
            this.setVelocity(this.v.getDx(), -this.v.getDy());
        }

    }

    /**
     * moves the ball one step in it's velocity, checks for collision on its way.
     * <p>
     * follows the collision algorithm of the task page:
     * 1) compute the ball trajectory (the trajectory is "how the ball will move
     * without any obstacles" -- its a line starting at current location, and
     * ending where the velocity will take the ball if no collisions will occur).
     * <p>
     * 2) Check (using the game environment) if moving on this trajectory will hit anything.
     * <p>
     * 2.1) If no, then move the ball to the end of the trajectory.
     * <p>
     * 2.2) Otherwise (there is a hit):
     * 2.2.2) move the ball to "almost" the hit point, but just slightly before it.
     * 2.2.3) notify the hit object (using its hit() method) that a collision occurred.
     * 2.2.4) update the velocity to the new velocity returned by the hit() method.
     * <p>
     * deals with the balls being 'stuck' inside a Interfaces.Collidable.
     * </p>
     */
    public void moveOneStep() {
        if (this.getVelocity() != null) {
            Line trajectory = new Line(this.getCenter(), this.getVelocity().applyToPoint(this.getCenter()));
            CollisionInfo closestCollision = this.getGameEnvironment().getClosestCollision(trajectory);


            if (closestCollision == null) {
                this.setCenter(this.getVelocity().applyToPoint(this.getCenter()));
            } else {
                // in case the ball is trapped inside a block or a paddle.
                if (closestCollision.collisionObject().getCollisionRectangle().isInRectangle(this.getCenter())) {
                    this.setCenter(closestCollision.collisionPoint());
                    System.out.println("in");
                } else {
                    Point hitPoint = closestCollision.collisionPoint();
                    // as we said. the radios would be so small so moving it to the hit point minus the half of the
                    // velocity would mean "almost to the collision point" as required, as well as far enough for the
                    // ball to 'escape' the hit Interfaces.Collidable. I tried other stuff like the ball's size or 2-3
                    // pixels but it didn't work, I stuck with this approach because I saw is works the best when I
                    // debugged.

                    this.setCenter(new Point(hitPoint.getX() - this.getVelocity().getDx() / 2, hitPoint.getY()
                            - this.getVelocity().getDy() / 2));
                }
                Collidable c = closestCollision.collisionObject();
                this.setVelocity(c.hit(this, closestCollision.collisionPoint(), this.getVelocity()));
            }
        }
    }

    /**
     * adds the ball to the input GameObjects.Game object. meaning adds it to the list of interfaces it implements.
     *
     * @param g a GameObjects.Game object. the GameObjects.Game we want to add the ball to.
     */
    public void addToGame(Game g) {
        if (g != null) {
            g.addSprite(this);
        }
    }

    /**
     * removes the ball from the input Game by removing it from the SpriteCollection.
     * @param g a Game object.
     */
    public void removeFromGame(Game g) {
        if (g != null) {
            g.removeSprite(this);
        }
    }
}