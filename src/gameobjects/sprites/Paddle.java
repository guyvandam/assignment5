package gameobjects.sprites;

import gameobjects.CollisionInfo;
import gameobjects.Game;
import gameobjects.GameEnvironment;
import velocity.Velocity;
import geometryshapes.Ball;
import geometryshapes.Line;
import geometryshapes.Point;
import geometryshapes.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-03-28.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private java.awt.Color color;
    private GameEnvironment gameEnvironment;
    private int numOfRegions;
    // the part of the width we want to move the paddle each time we press the right or left keys.
    private int partOfWidth;

    /**
     * constructor function.
     *
     * @param keyboard        a GUI keyboard object.
     * @param rect            a GeometryShapes.Rectangle object.
     * @param color           a java.awt.color object.
     * @param gameEnvironment a GameObjects.GameEnvironment object.
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rect, Color color, GameEnvironment gameEnvironment) {
        this.keyboard = keyboard;
        this.rect = rect;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
        this.numOfRegions = 5;
        this.partOfWidth = 6;
    }

    /**
     * @return an integer. the numbers of regions the paddle have.
     */
    public int getNumOfRegions() {
        return numOfRegions;
    }

    /**
     * @return an integer. the part of the width we want to move.
     */
    public int getPartOfWidth() {
        return this.partOfWidth;
    }

    /**
     * @return a GeometryShapes.Rectangle object.
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * @return a keyboardSensor object.
     */
    public KeyboardSensor getKeyboard() {
        return keyboard;
    }

    /**
     * @return a GameObjects.GameEnvironment object. the paddle's game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    /**
     * moves the paddle one step to the left
     * <p>
     * checks for corner hits using the 'getClosestIntersection' function. if it senses there's a hit. moves the
     * paddle to the hit point.
     * </p>
     */
    public void moveLeft() {
        Point upperLeft = this.getRect().getUpperLeft(),
                oneStep = new Point(upperLeft.getX() - this.getRect().getWidth() / this.getPartOfWidth(),
                        upperLeft.getY());

        Line trajectory = new Line(upperLeft, oneStep);
        CollisionInfo closestCollision = this.getGameEnvironment().getClosestCollision(trajectory, this);
        if (closestCollision == null) {
            this.getRect().setUpperLeft(trajectory.end());
        } else {
            this.getRect().setUpperLeft(closestCollision.collisionPoint());
        }
    }

    /**
     * moves the paddle one step to the right
     * <p>
     * checks for corner hit using the 'getClosestIntersection' function between the right edge and the right edge
     * of the future step, if there's a hit, moves the right edge to the collision point - the paddle itself to the
     * same point minus the width of the paddle in the x-axis.
     * </p>
     */
    public void moveRight() {
        Point upperRight = this.getRect().getUpperRight(), upperLeft = this.getRect().getUpperLeft(),
                oneStepR = new Point(upperRight.getX() + this.getRect().getWidth() / this.getPartOfWidth(),
                        upperRight.getY()),
                oneStep = new Point(upperLeft.getX() + this.getRect().getWidth() / this.getPartOfWidth(),
                        upperLeft.getY());

        Line trajectory = new Line(upperRight, oneStepR);

        CollisionInfo closestCollision = this.getGameEnvironment().getClosestCollision(trajectory, this);
        if (closestCollision == null) {
            this.getRect().setUpperLeft(oneStep);
        } else {
            this.getRect().setUpperLeft(new Point(closestCollision.collisionPoint().getX()
                    - this.getRect().getWidth(), closestCollision.collisionPoint().getY()));
        }
    }

    // Interfaces.Sprite
    @Override
    public void timePassed() {
        if (this.getKeyboard().isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (this.getKeyboard().isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (d != null) {
            d.setColor(this.color);
            d.fillRectangle((int) this.getRect().getUpperLeft().getX(), (int) this.getRect().getUpperLeft().getY(),
                    (int) this.getRect().getWidth(), (int) this.getRect().getHeight());
        }
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null || currentVelocity == null) {
            return null;
        }
        Line right = this.getCollisionRectangle().getRightEdge();
        Line left = this.getCollisionRectangle().getLeftEdge();
        Line upper = this.getCollisionRectangle().getUpperEdge();
        Line lower = this.getCollisionRectangle().getLowerEdge();

        if (left.isInLine(collisionPoint) || right.isInLine(collisionPoint)) {

            currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        if (upper.isInLine(collisionPoint) || lower.isInLine(collisionPoint)) {
            Line edge = upper.isInLine(collisionPoint) ? upper : lower;
            int region = this.getRegion(edge, collisionPoint);
            double speed = Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                    + currentVelocity.getDy() * currentVelocity.getDy());

            switch (region) {
                case 1:
                    return Velocity.fromAngleAndSpeed(210, speed);
                case 2:
                    return Velocity.fromAngleAndSpeed(240, speed);
                case 3:
                    currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                    break;
                case 4:
                    return Velocity.fromAngleAndSpeed(300, speed);
                default:
                    return Velocity.fromAngleAndSpeed(330, speed);

            }
        }
        return currentVelocity;
    }

    /**
     * returns the number of region the point is located on the line.
     *
     * @param edge  a GeometryShapes.Line object. representing the paddle upper edge.
     * @param point a GeometryShapes.Point object.
     * @return an integer. the number of the region.
     */
    public int getRegion(Line edge, Point point) {
        if (edge == null || point == null) {
            return 0;
        }
        double dist = edge.start().distance(point);
        int i = 1;
        double lengthOfRegion = this.getRect().getWidth() / this.getNumOfRegions();
//        while (i < numOfRegions && dist >= lengthOfRegion * i) {
        while (i <= this.getNumOfRegions() && dist > lengthOfRegion * i) {
            i++;
        }
        return i;
    }

    /**
     * adds the paddle to the input GameObjects.Game object , meaning adds it to the list of interfaces it implements.
     *
     * @param g a GameObjects.Game object.
     */
    public void addToGame(Game g) {
        if (g != null) {
            g.addSprite(this);
            g.addCollidable(this);
        }
    }
}