package GameObjects;

import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-03-28.
 */

public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * constructor function. initialize the Interfaces.Sprite ArrayList.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * adds the input Interfaces.Sprite the Interfaces.Sprite ArrayList.
     *
     * @param s a Interfaces.Sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * @return a ArrayList object. the Interfaces.Sprite ArrayList.
     */
    public List<Sprite> getSprites() {
        return sprites;
    }

    /**
     * calls the 'timePassed' function for all the Sprites in the list.
     */
    public void notifyAllTimePassed() {
        for (Sprite s : this.getSprites()) {
            s.timePassed();
        }
    }

    /**
     * calls the 'drawOn' function for all the Sprites in the list.
     *
     * @param d a drawSurface object. the surface we want to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.getSprites()) {
            s.drawOn(d);
        }
    }
}