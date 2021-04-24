package model;

public class Random {

    private int random;
    private Random next;

    public Random(int random) {
        this.random = random;
    }

    public int getRandom() {
        return random;
    }

    public void setRandom(int random) {
        this.random = random;
    }

    public Random getNext() {
        return next;
    }

    public void setNext(Random next) {
        this.next = next;
    }
}
