package interfaces;
import biuoop.DrawSurface;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-03-28.
 */

public interface Sprite {


    /**
     * draws the sprite on the input surface.
     *
     * @param d a DrawSurface object. the GUI surface we want to draw the sprite on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite the time has passed.
     */
    void timePassed();
}