package model;

public class Leader {
    
    private int startL;
    private int endL;
    
    private Leader next;

    public Leader(int startL, int endL) {
        this.endL = endL;
        this.next = next;
    }

    public int getStartL() {
        return startL;
    }

    public void setStartL(int startL) {
        this.startL = startL;
    }

    public int getEndL() {
        return endL;
    }

    public void setEndL(int endL) {
        this.endL = endL;
    }

    public Leader getNext() {
        return next;
    }

    public void setNext(Leader next) {
        this.next = next;
    }
}
