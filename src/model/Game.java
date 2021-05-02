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
    
    /**
     * Load the leaderboard
     * @throws IOException
     * @throws FileNotFoundException 
     */

    public void loadData() throws IOException, FileNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(SAVE_PATH_FILE)));
            Player test = (Player) ois.readObject();
            this.podium = test;
            ois.close();
        } catch (Exception e) {
        }
    }
    
    /**
     * Saves the players to be serialized
     * @throws IOException 
     */

    public void saveData() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE));
        oos.writeObject(this.podium);
        oos.close();
    }

    /**
     * Builder Game
     * @throws IOException 
     */
    
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

    /**
     * Create the matrix
     * @param row number of rows
     * @param col number of columns
     */
    
    public void newMatrix(int row, int col) {
        matrix = new Matrix(row, col);
    }

    /**
     * Show the matrix
     * @return 
     */
    
    public String soutMatrix() {
        return matrix.toStringMatrix();
    }

    /**
     * Create the snakes
     * @param row number of rows
     * @param col number or columns
     * @param ammountSnakes amount of snakes to create
     */
    
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

    /**
     * Create the ladder 
     * @param row number of rows
     * @param col number of columns
     * @param amountLeaders amount of ladders to create
     */
    
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

    /**
     * Create the players
     * @param n amount of players
     */
    
    public void createPlayers(int n) {
        if (n > 0) {
            Nodo pos = matrix.searchNode(1);
            pos.createPlayer(pos, param);
            n -= 1;
            firstPlayerGame = pos.getFirstPlayer();
            createPlayers(n);
        }
    }

    /**
     * Create the player with symbols
     * @param param symbols
     */
    
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

    /**
     * A random position for snakes and ladders is obtained.
     * @param maxNum Max limit
     * @param minNum Min limit
     * @return 
     */
    
    public int addRandomInt(int maxNum, int minNum) {
        int randomPos = (int) Math.floor(Math.random() * (maxNum - minNum) + minNum);
        if (addRandom(randomPos)) {
            return randomPos;
        } else {
            return addRandomInt(maxNum, minNum);
        }
    }

    /**
     * Searches if the random number has already been selected before.
     * @param rd LinkedList to search the random
     * @param random rando to search
     * @return boolean to know if the rando is never used
     */
    
    private boolean searchRandom(Random rd, int random) {
        if (rd.getRandom() == random) {
            return true;
        } else if (rd.getNext() != null) {
            return searchRandom(rd.getNext(), random);
        } else {
            return false;
        }
    }

    /**
     * Add the random in the linkedList and add create snake or ladder
     * @param random random
     * @return boolean to know if the random is add it
     */
    
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

    /**
     * Metodh recursive to add random
     * @param rd random to add
     * @return recursive
     */
    
    private Random getLastRandom(Random rd) {
        if (rd.getNext() == null) {
            return rd;
        } else {
            return getLastRandom(rd.getNext());
        }
    }

    /**
     * Roll the die and get the number of the die
     * @return  number of dice
     */
    
    public int throwDice() {
        int maxDice = 7;
        int minDice = 1;
        int dice = (int) Math.floor(Math.random() * (maxDice - minDice) + minDice);
        return dice;
    }

    /**
     * Get the symbol player
     * @return 
     */
    
    public Player getSymbolPlayer() {
        return searchPlayerTurn();
    }

    /**
     * Changes the players in the boxes.
     * @param dice value of dice
     */
    
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

    /**
     * Perform the action to move the die
     * @param py Player to move
     * @param numL new position
     */
    
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

    /**
     * Create new player to add in the box.
     * @param py reference player
     * @return player created
     */
    
    public Player createNewPlayerinNodo(Player py) {
        Player newPlayer = new Player(py.getSymbol(), py.getAmountPlay(), py.getPosition(), py.isFinish(),
                py.getParam(), py.getTurn(), py.getNickName(), py.getScore());
        newPlayer.setNext(null);
        return newPlayer;
    }
    
    /**
     * Search for the player whose turn it is.
     * @return actual player
     */

    public Player searchPlayerTurn() {
        if (firstPlayerGame.getTurn() == actualTurn) {
            return firstPlayerGame;
        } else {
            return searchPlayerTurn(firstPlayerGame, actualTurn);
        }
    }

    /**
     * Recursive to search the player
     * @param py first Player
     * @param tourn tourn actual
     * @return actual tourn
     */
    
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

    /**
     * Change any atributes of the winner player
     * @param py winner player
     * @param nickName nickname winner player
     * @param row number of row
     * @param col number of columns
     * @param param  params winner player
     */
    
    public void askPlayer(Player py, String nickName, int row, int col, String param) {
        String newParam = param;
        newParam+= " "+showSyms();
        py.setScore(row, col);
        py.setNickName(nickName);
        py.setParam(newParam);
        py.setFinish(true);
    }

    public boolean getFinishGame() {
        return finishGame;
    }
    
    /**
     * Add winner player in the Binary Search Tree
     * @param py winner player
     * @throws IOException 
     */

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

    /**
     * Recurive add Binary Search Tree
     * @param current root tree
     * @param newPlayer player to add
     */
    
    private void addBinary(Player current, Player newPlayer) {
        if (newPlayer.getScore() <= current.getScore()) {
            if (current.getRight() == null) {
                current.setRight(newPlayer);
                newPlayer.setParent(current);
            } else {
                addBinary(current.getRight(), newPlayer);
            }
        } else {
            if (current.getLeft() == null) {
                current.setLeft(newPlayer);
                newPlayer.setParent(current);
            } else {
                addBinary(current.getLeft(), newPlayer);
            }
        }
    }
    private String playerSim = "";
    private String stringTree = "";
    private int positionInTree = 1;
    
    /**
     * Show the Binary Search Tree
     * @param py root
     * @return positions
     */

    public String showTreeInOrder(Player py) {
        if (py != null) {
            showTreeInOrder(py.getLeft());
            stringTree += "-------------------["+(positionInTree++)+"]----------------------\n"; 
            stringTree += "NickName: " + py.getNickName() + "\nSimbolo: " + py.getSymbol()+ "\nParametros: " + py.getParam()+"\n";
            stringTree += "Puntaje: " + py.getScore() + "\n";
            stringTree += "--------------------------------------------\n";
            showTreeInOrder(py.getRight());
        }
        return stringTree;
    }

    public Player getPodium() {
        return podium;
    }

    /**
     * Show the syms of each player
     * @return Symbol's players
     */
    
    public String showSyms() {
        if (firstPlayerGame == null) {
            playerSim = "";
        } else if (firstPlayerGame != null) {
            playerSim += firstPlayerGame.getSymbol();
            if (firstPlayerGame.getNext() != null) {
                showSyms(firstPlayerGame.getNext());
            }
        }
        return playerSim;
    }

    /**
     * Recursive show symbols
     * @param player Symbol's players
     */
    
    private void showSyms(Player player) {
        if (player != null) {
            playerSim += player.getSymbol();
            showSyms(player.getNext());
        }
    }
} 