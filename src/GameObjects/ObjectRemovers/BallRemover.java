package GameObjects.ObjectRemovers;

import GameObjects.Block;
import GameObjects.Counter;
import GameObjects.Game;
import GeometryShapes.Ball;
import Interfaces.HitListener;

public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    public BallRemover(Game game, Counter counter) {
        this.game = game;
        this.remainingBalls = counter;
    }

    public Game getGame() {
        return game;
    }

    public Counter getCounter() {
        return remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if(beingHit == null || hitter == null){
            return;
        }
        hitter.removeFromGame(this.getGame());
        remainingBalls.decrease(1);
    }
}
