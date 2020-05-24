package GameObjects;

import GeometryShapes.Line;
import GeometryShapes.Point;
import GeometryShapes.Rectangle;
import Interfaces.Collidable;
import Interfaces.HitListener;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-03-28.
 */
public class Block implements Collidable, Sprite {
    private Rectangle rect;
    private java.awt.Color color;

    /**
     * constructor function.
     *
     * @param rect  a GeometryShapes.Rectangle object.
     * @param color a java.awt.Color object.
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }

    /**
     * @return a GeometryShapes.Rectangle object. the block's GeometryShapes.Rectangle value.
     */
    public Rectangle getRect() {
        return rect;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return getRect();
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null || currentVelocity == null) {
            return null;
        }
        Line right = this.getCollisionRectangle().getRightEdge();
        Line left = this.getCollisionRectangle().getLeftEdge();
        Line upper = this.getCollisionRectangle().getUpperEdge();
        Line lower = this.getCollisionRectangle().getLowerEdge();

        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        if (left.isInLine(collisionPoint) || right.isInLine(collisionPoint)) {
            dx = -dx;
        }
        if (upper.isInLine(collisionPoint) || lower.isInLine(collisionPoint)) {
            dy = -dy;
        }

        return new Velocity(dx, dy);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        if (surface != null) {
            surface.setColor(Color.BLACK);
            surface.drawRectangle((int) this.getRect().getUpperLeft().getX(), (int)
                            this.getRect().getUpperLeft().getY(),
                    (int) this.getRect().getWidth(), (int) this.getRect().getHeight());
            surface.setColor(this.color);
            surface.fillRectangle((int) this.getRect().getUpperLeft().getX() + 1, (int)
                            this.getRect().getUpperLeft().getY() + 1,
                    (int) this.getRect().getWidth() - 1, (int) this.getRect().getHeight() - 1);


        }
    }

    @Override
    public void timePassed() {
    }

    /**
     * adds the GameObjects.Block to the input GameObjects.Game object.
     *
     * @param g a GameObjects.Game object. the GameObjects.Game we want to add the GameObjects.Block to.
     */
    public void addToGame(Game g) {
        if (g != null) {
            g.addCollidable(this);
            g.addSprite(this);
        }
    }

    public void removeFromGame(Game g) {
        if (g != null) {
            g.removeCollidable(this);
            g.removeSprite(this);
        }
    }
}
