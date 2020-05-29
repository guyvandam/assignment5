package interfaces;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-05-28.
 */
public interface HitNotifier {
    /**
     * Adds the input HitListener object hl, as a listener to hit events.
     *
     * @param hl a HitListener object.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes the input HitListener hl, from the list of listeners to hit events.
     *
     * @param hl a HitListener object.
     */
    void removeHitListener(HitListener hl);
}
