package gameobjects.sprites;

import gameobjects.Counter;
import gameobjects.Game;
import interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-05-28.
 */
public class ScoreIndicator implements Sprite {
    private Counter currentScore;
    private Block block;

    /**
     * constructor function.
     *
     * @param currentScore a Counter object.
     * @param block        a Block object.
     */
    public ScoreIndicator(Counter currentScore, Block block) {
        this.currentScore = currentScore;
        this.block = block;
    }

    /**
     * @return a Counter object.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }


    /**
     * @return a Block object.
     */
    public Block getBlock() {
        return block;
    }

    /**
     * draws the sprite on the input surface.
     *
     * @param d a DrawSurface object. the GUI surface we want to draw the sprite on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        String display = "Score: " + this.getCurrentScore().getValue();
        int fontSize = 15, x = (int) this.getBlock().getRect().getWidth() / 2, y = fontSize;
        if (d != null) {
            this.getBlock().drawOn(d);
            d.setColor(Color.BLACK);
            d.drawText(x, y, display, fontSize);
        }
    }

    /**
     * notify the sprite the time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * adds the ScoreIndicator object to the input Game be adding it to the Game's spriteCollection.
     *
     * @param g a Game object.
     */
    public void addToGame(Game g) {
        if (g == null) {
            return;
        }
        g.addSprite(this);
    }
}
