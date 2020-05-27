package OutputClasses;

import GameObjects.Block;
import GeometryShapes.Ball;
import Interfaces.HitListener;

public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
