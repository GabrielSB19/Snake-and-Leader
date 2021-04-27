package model;

public class Nodo {

    private int row;
    private int col;
    private int num;

    private Nodo next;
    private Nodo prev;
    private Nodo up;
    private Nodo down;
    private Leader leader;
    private Snake snake;
    private Player firstPlayer;

    public Nodo(int row, int col, int num) {
        this.row = row;
        this.col = col;
        this.num = num;
        this.leader = null;
        this.snake = null;
        this.firstPlayer = null;
    }

    public void setStair(Leader stair) {
        this.leader = stair;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Snake getSnake() {
        return snake;
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
        if (snake == null && leader == null) {
            return "[" + num + "]";
        } else if (snake != null) {
            return "[" + num + snake.getIdS() + "]";
        } else {
            return "[" + num + leader.getIdL() + "]";
        }
    }

    public String toStringGame() {
        if (snake == null && leader == null) {
            return "[ ]";
        } else if (snake != null) {
            return "[" + snake.getIdS() + "]";
        } else {
            return "[" + leader.getIdL() + "]";
        }
    }
}
