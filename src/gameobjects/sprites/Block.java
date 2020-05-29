package gameobjects.sprites;

import gameobjects.Game;
import velocity.Velocity;
import geometryshapes.Ball;
import geometryshapes.Line;
import geometryshapes.Point;
import geometryshapes.Rectangle;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-03-28.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    /**
     * constructor function.
     *
     * @param rect  a GeometryShapes.Rectangle object.
     * @param color a java.awt.Color object.
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @return a GeometryShapes.Rectangle object. the block's GeometryShapes.Rectangle value.
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * @return a list of HitListener objects.
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return getRect();
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

        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        if (left.isInLine(collisionPoint) || right.isInLine(collisionPoint)) {
            dx = -dx;
        }
        if (upper.isInLine(collisionPoint) || lower.isInLine(collisionPoint)) {
            dy = -dy;
        }
        this.notifyHit(hitter);
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

    /**
     * removes the Block from the input Game by removing it from the Game's SpriteCollection and and.
     *
     * @param g a Game object.
     */
    public void removeFromGame(Game g) {
        if (g != null) {
            g.removeCollidable(this);
            g.removeSprite(this);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        if (this.hitListeners.isEmpty()) {
            return;
        }
        this.hitListeners.remove(hl);
    }

    /**
     * notifies all the hitListeners of the Block that a hit occurred, loops through a copy of the Block's list in case
     * there will be change mid-loop.
     *
     * @param hitter a Ball object. the Ball hitting the Block.
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.getHitListeners());
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
