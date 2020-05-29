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
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * constructor function.
     *
     * @param game            a Game object.
     * @param remainingBlocks a Counter object.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * @return a Game object.
     */
    public Game getGame() {
        return this.game;
    }

    /**
     *
     * @return a Counter object, representing the number of remaining blocks in the game.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit == null || hitter == null) {
            return;
        }
        beingHit.removeFromGame(this.getGame());
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}