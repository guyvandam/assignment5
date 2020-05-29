package gameobjects.hitlisteners;

import gameobjects.sprites.Block;
import gameobjects.Counter;
import geometryshapes.Ball;
import interfaces.HitListener;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-05-28.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor method.
     *
     * @param scoreCounter a Counter object.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}