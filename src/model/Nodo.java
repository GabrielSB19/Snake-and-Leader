package model;

public class Nodo {

    private int row;
    private int col;
    private int num;
    private String players = "";

    private int asciiPlayers = 33;
    private int lengthPlayer = 0;
    private int turn = 1;
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
    
    public void setPlayerString(String s){
        players = s;
    }

    public void setFirstPlayer(Player py) {
        firstPlayer = py;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
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
        stringPlayer();
        String aux = players;
        if (snake == null && leader == null) {
            players = "";
            return "[" + aux + "]";
        } else if (snake != null) {
            players = "";
            return "[" + snake.getIdS() + aux + "]";
        } else {
            players = "";
            return "[" + leader.getIdL() + aux + "]";
        }
        
    }

    public void createPlayer(Nodo pos, String param) {
        Player newPlayer = new Player((char) asciiPlayers++, 0, pos, false, param, turn++);
        if (firstPlayer == null) {
            firstPlayer = newPlayer;
        } else {
            addPlayers(firstPlayer, newPlayer);
        }
    }

    public void createPlayerSymb(Nodo pos, String param, int n) {
        Player newPlayer = new Player(param.charAt(n), 0, pos, false, param, turn++);
        if (firstPlayer == null) {
            firstPlayer = newPlayer;
        } else {
            addPlayers(firstPlayer, newPlayer);
        }
    }

    private void addPlayers(Player py, Player newPy) {
        if (py.getNext() == null) {
            py.setNext(newPy);
        } else {
            addPlayers(py.getNext(), newPy);
        }
    }

    public void listPlayer(Player player) {
        if (firstPlayer == null) {
            firstPlayer = player;
        } else {
            addPlayerNext(firstPlayer, player);
        }
    }

    private void addPlayerNext(Player current, Player player) {
        if (current.getNext() == null) {
            current.setNext(player);
        } else {
            addPlayerNext(current.getNext(), player);
        }
    }

    public void stringPlayer() {
        if (firstPlayer == null) {
            players = "";
        } else if (firstPlayer != null) {
            players += firstPlayer.getSymbol();
            if (firstPlayer.getNext() != null) {
                stringPlayer(firstPlayer.getNext());
            }
        }
    }

    private void stringPlayer(Player player) {
        if (player != null) {
            players += player.getSymbol();
            stringPlayer(player.getNext());
        }
    }

    public void removePlayerNodo(char py) {
        if (firstPlayer.getSymbol() == py) {
            if (firstPlayer.getNext() == null) {
                firstPlayer = null;
                players = "";
            } else {
                Player current = firstPlayer.getNext();
                firstPlayer = current;
                players = "";
            }

        } else {
            removePlayer(firstPlayer, py);
        }
    }

    private void removePlayer(Player player, char py) {
        if(player != null){
            if(player.getSymbol() == py){
                Player current = player.getNext();
                player = current;
                players = "";
            } else {
                removePlayer(player.getNext(), py);
            }
        }
    }

    public void addPlayerNodo(Player temp) {
        if (firstPlayer == null) {
            firstPlayer = temp;
        } else {
            addPlayers(firstPlayer, temp);
        }
    }
}
