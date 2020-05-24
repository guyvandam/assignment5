package Interfaces;

import GameObjects.Block;
import GeometryShapes.Ball;

public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.
    void hitEvent(Block beingHit, Ball hitter);
}