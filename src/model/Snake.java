package model;

public class Snake {
    
    private int startS;
    private int endS;
    
    private Snake next;

    public Snake(int startS, int endS) {
        this.startS = startS;
        this.endS = endS;
    }

    public int getStartS() {
        return startS;
    }

    public void setStartS(int startS) {
        this.startS = startS;
    }

    public int getEndS() {
        return endS;
    }

    public void setEndS(int endS) {
        this.endS = endS;
    }

    public Snake getNext() {
        return next;
    }

    public void setNext(Snake next) {
        this.next = next;
    }
}
