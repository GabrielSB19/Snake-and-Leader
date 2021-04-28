package model;

public class Player {

    private char symbol;
    private int amountPlay;
    private Nodo position;
    private boolean finish;
    private String param;
    private int turn;
    private Player next;
    private Player left;
    private Player right;
    private Player parent;

    public Player(char symbol, int amountPlay, Nodo position, boolean finish, String param, int turn) {
        this.symbol = symbol;
        this.amountPlay = amountPlay;
        this.position = position;
        this.finish = false;
        this.param = param;
        this.turn = turn;
    }
    
    public int getTurn() {
        return turn;
    }
    
    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getAmountPlay() {
        return amountPlay;
    }

    public void setAmountPlay(int amountPlay) {
        this.amountPlay = amountPlay;
    }

    public Nodo getPosition() {
        return position;
    }

    public void setPosition(Nodo position) {
        this.position = position;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public Player getNext() {
        return next;
    }

    public void setNext(Player next) {
        this.next = next;
    }

    public Player getLeft() {
        return left;
    }

    public void setLeft(Player left) {
        this.left = left;
    }

    public Player getRight() {
        return right;
    }

    public void setRight(Player right) {
        this.right = right;
    }

    public Player getParent() {
        return parent;
    }

    public void setParent(Player parent) {
        this.parent = parent;
    }
}
