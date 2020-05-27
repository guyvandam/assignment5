package GameObjects.ObjectRemovers;

import GameObjects.Block;
import GameObjects.Game;
import GeometryShapes.Ball;
import Interfaces.HitListener;
import GameObjects.Counter;

public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game  = game;
        this.remainingBlocks = remainingBlocks;
    }

    public Game getGame() {
        return game;
    }

    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        if(beingHit == null || hitter == null){
            return;
        }
        beingHit.removeFromGame(this.getGame());
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}