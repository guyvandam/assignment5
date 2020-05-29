package gameobjects.hitlisteners;

import gameobjects.sprites.Block;
import gameobjects.Counter;
import gameobjects.Game;
import geometryshapes.Ball;
import interfaces.HitListener;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-05-28.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * constructor method.
     *
     * @param game    a Game object.
     * @param counter a Counter object.
     */
    public BallRemover(Game game, Counter counter) {
        this.game = game;
        this.remainingBalls = counter;
    }

    /**
     * @return a Game object.
     */
    public Game getGame() {
        return game;
    }

    /**
     * @return a Counter object.
     */
    public Counter getCounter() {
        return remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit == null || hitter == null) {
            return;
        }
        hitter.removeFromGame(this.getGame());
        remainingBalls.decrease(1);
    }
}
