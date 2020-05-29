package gameobjects;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-05-28.
 */
public class Counter {
    private int counter;

    /**
     * constructor function. sets the number to 0.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * constructor function.
     *
     * @param counter an Integer, the starting count.
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * adds the input number to the current count.
     *
     * @param number an Integer.
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * subtract the input number from the current count.
     *
     * @param number an Integer.
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * @return an Integer. the current Count.
     */
    public int getValue() {
        return this.counter;
    }
}