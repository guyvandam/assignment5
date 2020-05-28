package OutputClasses;

import GameObjects.Block;
import GeometryShapes.Ball;
import Interfaces.HitListener;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-05-28.
 */
public class PrintingHitListener implements HitListener {
    /**
     * @param beingHit a BlockObject.
     * @param hitter   a Ball object.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
