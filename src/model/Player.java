package model;

public class Player {
    
    private String symbol;
    private int amountPlay;
    private int position;
    private boolean finish;
    
    private Player next;
    private Player left;
    private Player right;
    private Player parent;

    public Player(String symbol, int amountPlay, int position, boolean finish) {
        this.symbol = symbol;
        this.amountPlay = amountPlay;
        this.position = position;
        this.finish = finish;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getAmountPlay() {
        return amountPlay;
    }

    public void setAmountPlay(int amountPlay) {
        this.amountPlay = amountPlay;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
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