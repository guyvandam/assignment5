package GameObjects;

public class Counter {
    int counter;

    public Counter() {
        this.counter = 0;
    }

    public Counter(int counter) {
        this.counter = counter;
    }

    // add number to current count.
    void increase(int number){
        counter+=number;
    }
    // subtract number from current count.
    void decrease(int number){
        counter-=number;
    }
    // get current count.
    int getValue(){
        return this.counter;
    }
}