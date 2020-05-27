package GameObjects;

import GeometryShapes.Ball;
import Interfaces.HitListener;
import org.w3c.dom.css.Counter;

public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    public BlockRemover(Game game, Counter removedBlocks) {
        this.game  = game;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
    }
}