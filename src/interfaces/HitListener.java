package interfaces;

import gameobjects.sprites.Block;
import geometryshapes.Ball;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-05-28.
 */
public interface HitListener {
    /**
     * This method is called whenever the 'beingHit' object is being hit by the 'hitter' object.
     *
     * @param beingHit a BlockObject.
     * @param hitter   a Ball object.
     */
    void hitEvent(Block beingHit, Ball hitter);
}