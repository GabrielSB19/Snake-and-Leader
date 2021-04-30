package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Game implements Serializable {

    private final String SAVE_PATH_FILE = "data/BinaryTree.cgd";

    public void loadData() throws IOException, FileNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(SAVE_PATH_FILE)));
            Player test = (Player) ois.readObject();
            this.podium = test;
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveData() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE));
        oos.writeObject(this.podium);
        oos.close();
    }

    public Game() throws IOException {
        loadData();
    }

    private int countSnakes = 0;
    private int countLeaders = 0;
    private Matrix matrix;
    private Random first;
    private String param;
    private int maxTurns;
    private int actualTurn = 1;
    private boolean snake = false;
    private boolean ladder = false;
    private boolean finishGame = false;
    private Player firstPlayerGame;
    private Player podium;

    public boolean getBooleanSnake() {
        return snake;
    }

    public boolean getBooleaLadder() {
        return ladder;
    }

    public boolean getBooleanFinishGame() {
        return finishGame;
    }

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
            firstPlayerGame = pos.getFirstPlayer();
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
            firstPlayerGame = pos.getFirstPlayer();
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
        int dice = (int) Math.floor(Math.random() * (maxDice - minDice) + minDice);
        return dice;
    }

    public Player getSymbolPlayer() {
        return searchPlayerTurn();
    }

    public void moveGame(int dice) {
        snake = false;
        ladder = false;
        finishGame = false;
        Player py = searchPlayerTurn();
        int numF = py.getPosition().getNum();
        Nodo nodoF = matrix.searchNode(numF);
        nodoF.removePlayerNodo(py.getSymbol());
        int numL = nodoF.getNum() + dice;
        if (numL > matrix.getNumCol() * matrix.getNumRow()) {
            py.setAmountPlay(py.getAmountPlay() + 1);
            actualTurn = py.getTurn();
            finishGame = true;
        } else if (numL == matrix.getNumCol() * matrix.getNumRow()) {
            action(py, numL);
            actualTurn = py.getTurn();
            finishGame = true;
        } else {
            action(py, numL);
            actualTurn++;
            if (actualTurn == maxTurns) {
                actualTurn = 1;
            }
        }
    }

    public void action(Player py, int numL) {
        py.setAmountPlay(py.getAmountPlay() + 1);
        Nodo nodoL = matrix.searchNode(numL);
        if (nodoL.getSnake() != null) {
            if (nodoL.getNum() == nodoL.getSnake().getFirstS().getNum()) {
                snake = true;
                nodoL = nodoL.getSnake().getLastS();
                py.setPosition(nodoL);
                nodoL.addPlayerNodo(createNewPlayerinNodo(py));
            } else {
                py.setPosition(nodoL);
                nodoL.addPlayerNodo(createNewPlayerinNodo(py));
            }
        } else if (nodoL.getLeader() != null) {
            if (nodoL.getNum() == nodoL.getLeader().getFirstL().getNum()) {
                ladder = true;
                nodoL = nodoL.getLeader().getLastL();
                py.setPosition(nodoL);
                nodoL.addPlayerNodo(createNewPlayerinNodo(py));
            } else {
                py.setPosition(nodoL);
                nodoL.addPlayerNodo(createNewPlayerinNodo(py));
            }
        } else {
            py.setPosition(nodoL);
            nodoL.addPlayerNodo(createNewPlayerinNodo(py));
        }
    }

    public Player createNewPlayerinNodo(Player py) {
        Player newPlayer = new Player(py.getSymbol(), py.getAmountPlay(), py.getPosition(), py.isFinish(),
                py.getParam(), py.getTurn(), py.getNickName(), py.getScore());
        newPlayer.setNext(null);
        return newPlayer;
    }

    public Player searchPlayerTurn() {
        if (firstPlayerGame.getTurn() == actualTurn) {
            return firstPlayerGame;
        } else {
            return searchPlayerTurn(firstPlayerGame, actualTurn);
        }
    }

    private Player searchPlayerTurn(Player py, int tourn) {
        if (py.getNext() != null) {
            if (py.getNext().getTurn() == tourn) {
                return py.getNext();
            } else {
                return searchPlayerTurn(py.getNext(), tourn);
            }
        }
        return null;
    }

    public void askPlayer(Player py, String nickName, int row, int col, String param) {
        py.setScore(row, col);
        py.setNickName(nickName);
        py.setParam(param);
        py.setFinish(true);
    }

    public boolean getFinishGame() {
        return finishGame;
    }

    public void addBinary(Player py) throws IOException {
        Player newPlayer = new Player(py.getSymbol(), py.getAmountPlay(), py.getPosition(), py.isFinish(), 
        py.getParam(), py.getTurn(), py.getNickName(), py.getScore());
        if (podium == null) {
            podium = newPlayer;
        } else {
            addBinary(podium, newPlayer);
        }
        saveData();
    }

    private void addBinary(Player current, Player newPlayer) {
        if (newPlayer.getScore() <= current.getScore()) {
            if (current.getLeft() == null) {
                current.setLeft(newPlayer);
                newPlayer.setParent(current);
            } else {
                addBinary(current.getLeft(), newPlayer);
            }
        } else {
            if (current.getRight() == null) {
                current.setRight(newPlayer);
                newPlayer.setParent(current);
            } else {
                addBinary(current.getRight(), newPlayer);
            }
        }
    }
}
