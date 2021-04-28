package model;

public class Game {

    private int countSnakes = 0;
    private int countLeaders = 0;
    private Matrix matrix;
    private Random first;
    private String param;
    private int maxTurns;
    private int actualTurn = 1;
    private Player firstPlayer;

    public void setMaxTurns(int n) {
        maxTurns = n;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public void setCountSnakes(int n) {
        countSnakes = n;
    }

    public void setCountLeader(int n) {
        countLeaders = n;
    }

    public void newMatrix(int row, int col) {
        matrix = new Matrix(row, col);
    }

    public String soutMatrix() {
        return matrix.toStringMatrix();
    }

    public void createSnakes(int row, int col, int ammountSnakes) {
        if (countSnakes < ammountSnakes) {
            int maxNumF = (matrix.getNumCol() * matrix.getNumRow());
            int minNumF = (matrix.getNumCol() + 1);
            int snakeFirst = addRandomInt(maxNumF, minNumF);
            Nodo maxNd = matrix.searchNode(snakeFirst);

            int maxNumL = matrix.getMaxNumLSnake(maxNd) + 1;
            int minNumL = 2;
            int snakeLast = addRandomInt(maxNumL, minNumL);
            Nodo minNd = matrix.searchNode(snakeLast);

            matrix.createSnake(maxNd, minNd);
            countSnakes++;
            createSnakes(row, col, ammountSnakes);
        }
    }

    public void createLeader(int row, int col, int amountLeaders) {
        if (countLeaders < amountLeaders) {
            int maxNumF = (matrix.getNumCol() * matrix.getNumRow()) - matrix.getNumCol();
            int minNumF = 2;
            int leaderFirst = addRandomInt(maxNumF, minNumF);
            Nodo maxNd = matrix.searchNode(leaderFirst);

            int maxNumL = matrix.getNumCol() * matrix.getNumRow();
            int minNumL = matrix.getMaxNumLLeader(maxNd);
            int leaderLast = addRandomInt(maxNumL, minNumL);
            Nodo minNd = matrix.searchNode(leaderLast);

            matrix.createLeader(maxNd, minNd);
            countLeaders++;
            createLeader(row, col, amountLeaders);
        }
    }

    public void createPlayers(int n) {
        if (n > 0) {
            Nodo pos = matrix.searchNode(1);
            pos.createPlayer(pos, param);
            n -= 1;
            createPlayers(n);
        }
    }

    public void createPlayerSymb(String param) {
        int lengthPlayer = param.length();
        int aux = 0;
        addPlayers(param, aux, lengthPlayer);
    }

    private void addPlayers(String param, int n, int lengthPlayer) {
        if (n < lengthPlayer) {
            Nodo pos = matrix.searchNode(1);
            pos.createPlayerSymb(pos, param, n);
            n += 1;
            addPlayers(param, n, lengthPlayer);
        }
    }

    public int addRandomInt(int maxNum, int minNum) {
        int randomPos = (int) Math.floor(Math.random() * (maxNum - minNum) + minNum);
        if (addRandom(randomPos)) {
            return randomPos;
        } else {
            return addRandomInt(maxNum, minNum);
        }
    }

    private boolean searchRandom(Random rd, int random) {
        if (rd.getRandom() == random) {
            return true;
        } else if (rd.getNext() != null) {
            return searchRandom(rd.getNext(), random);
        } else {
            return false;
        }
    }

    private boolean addRandom(int random) {
        if (first == null) {
            first = new Random(random);
            return true;
        } else {
            if (!searchRandom(first, random)) {
                Random last = getLastRandom(first);
                last.setNext(new Random(random));
                return true;
            } else {
                return false;
            }
        }
    }

    private Random getLastRandom(Random rd) {
        if (rd.getNext() == null) {
            return rd;
        } else {
            return getLastRandom(rd.getNext());
        }
    }

    public int throwDice() {
        int maxDice = 7;
        int minDice = 1;
        //MaxDice - 1
        int dice = (int) Math.floor(Math.random() * (maxDice - minDice) + minDice);
        return dice;
    }

    public void setPlayersGame() {
        Nodo first = matrix.searchNode(1);
        firstPlayer = first.getFirstPlayer();
    }

    public Player getSymbolPlayer() {
        Player temp = firstPlayer;
        while (temp != null) {            
            System.out.println(temp.getSymbol() + " plis");
            temp = temp.getNext();
        }
        Nodo nodo = matrix.searchNode(1);
        return searchPlayerTurn(firstPlayer);
    }

    public void moveGame(Player py, int dice) {
        Nodo nodoF = matrix.searchNode(py.getPosition().getNum());
        Nodo nodoL = matrix.searchNode(nodoF.getNum() + dice);
        nodoF.removePlayerNodo(py);
        nodoL.addPlayerNodo(py);
        actualTurn++;
        actualTurn = (actualTurn == maxTurns) ? 1 : actualTurn;
    }

    private Player searchPlayerTurn(Player py) {
        System.out.println(py.getTurn());
        System.out.println(actualTurn);
        if (py.getTurn() == actualTurn) {
            return py;
        } else {
            return searchPlayerTurn(py.getNext());
        }
    }
}
