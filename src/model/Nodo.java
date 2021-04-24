package model;

public class Nodo {

    private int row;
    private int col;
    private int num;

    private Nodo next;
    private Nodo prev;
    private Nodo up;
    private Nodo down;

    public Nodo(int row, int col, int num) {
        this.row = row;
        this.col = col;
        this.num = num;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }

    public Nodo getPrev() {
        return prev;
    }

    public void setPrev(Nodo prev) {
        this.prev = prev;
    }

    public Nodo getUp() {
        return up;
    }

    public void setUp(Nodo up) {
        this.up = up;
    }

    public Nodo getDown() {
        return down;
    }

    public void setDown(Nodo down) {
        this.down = down;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String toString() {
        return "[" + num + "]";
    }
}
