package ui;

import java.io.IOException;
import model.*;

import java.util.Scanner;

/**
 * Menu main where all methods are performed
 *
 * @author Gabriel Suarez
 * @author David Montaño
 */
public class MenuMain {

    private Scanner sc;
    private Game gm;

    String[] params = new String[5];
    private int row = 0;
    private int col = 0;

    /**
     * Welcome method.
     */
    public static void welcome() {
        System.out.println("--------------------------------------------");
        System.out.println("--------------- Bienvenido A ---------------");
        System.out.println("---------- Escalares y Serpientes ----------");
        System.out.println("--------------------------------------------");
    }

    /**
     * MenuMain Builder
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public MenuMain() throws IOException, ClassNotFoundException {
        this.sc = new Scanner(System.in);
        this.gm = new Game();
    }

    /**
     * Displays the main options of the game.
     */
    public void mainMenu() {
        System.out.println("Que deseas hacer?");
        System.out.println("[1] Jugar");
        System.out.println("[2] Ver Puntuaciones");
        System.out.println("[3] Instrucciones");
        System.out.println("[4] Salir");
        System.out.println("--------------------------------------------");
    }

    /**
     * Read the option to execute
     *
     * @return choice a number from 1 to 4 is obtained
     */
    public int readOption() {
        int choice = Integer.parseInt(sc.nextLine());
        return choice;
    }

    /**
     * Executed the option selected.
     *
     * @param choice a numer from 1 to 4 es obtained
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void doOperation(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 1:
                System.out.println("Por favor ingresa los parametros del juego en una cadena");
                params(sc.nextLine());
                functionsGame();
                startGame();
                playTheGame();
                break;
            case 2:
                System.out.println("------------ Tabla de Posiciones -----------");
                System.out.println("--------------------------------------------");
                System.out.println(gm.showTreeInOrder(gm.getPodium()));
                break;
            case 3:
                showInstruccion();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Por favor selecciona una opcion correcta");
                break;
        }
    }

    /**
     * Inith the game
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("InfiniteRecursion")
    public void startProgram() throws IOException, ClassNotFoundException {
        mainMenu();
        int option = readOption();
        doOperation(option);
        startProgram();
    }

    /**
     * Gets the game conditions
     *
     * @param param A string with the following conditions m n s l p
     */
    public void params(String param) {
        gm.setParam(param);
        params = param.split(" ");
    }

    /**
     * Game configuration.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void functionsGame() throws IOException, ClassNotFoundException {
        newMatrix();
        if ((Integer.parseInt(params[2]) * 2) + (Integer.parseInt(params[3]) * 2) <= (row * col) - 2) {
            createSnakes();
            createLeader();
            createPlayers();
            soutMatrix();
        } else {
            System.out.println("El numero de escaleras y serpientes supera las casillas disponibles");
            System.out.println("Por favor, ingresa una menor cantidad");
            startProgram();
        }
    }

    /**
     * Moves the player to the obtained box.
     *
     * @param py Player will move.
     * @param dice Luck of the dice.
     * @return boolean to know the status of the game.
     */
    public boolean moveGame(Player py, int dice) {
        if (!gm.getBooleanFinishGame()) {
            gm.moveGame(dice);
            showMsgLadder(py.getSymbol());
            showMsgSnake(py.getSymbol());
            return false;
        } else {
            showMsgFinish(py);
            return true;
        }
    }

    /**
     * Create the snakes with the params[2].
     */
    public void createSnakes() {
        int ammountSnake = Integer.parseInt(params[2]);
        gm.createSnakes(row, col, ammountSnake);
        gm.setCountSnakes(0);
    }

    /**
     * Create the scales with params[3].
     */
    public void createLeader() {
        int amountLeader = Integer.parseInt(params[3]);
        gm.createLeader(row, col, amountLeader);
        gm.setCountLeader(0);
    }

    /**
     * Gets the player to move.
     *
     * @return Player
     */
    public Player getPlayer() {
        return gm.getSymbolPlayer();
    }

    /**
     * Creates players.
     */
    public void createPlayers() {
        try {
            int ammountPlayers = Integer.parseInt(params[4]);
            gm.createPlayers(ammountPlayers);
            gm.setMaxTurns(ammountPlayers + 1);
        } catch (NumberFormatException e) {
            gm.createPlayerSymb(params[4]);
            gm.setMaxTurns(params[4].length() + 1);
        }
    }

    /**
     * Creates Matrix.
     */
    public void newMatrix() {
        row = Integer.parseInt(params[0]);
        col = Integer.parseInt(params[1]);
        gm.newMatrix(row, col);
    }

    /**
     * Show the Matrx in the game.
     */
    public void soutMatrix() {
        System.out.println(gm.soutMatrix());
    }

    /**
     * Roll the die to move the player-
     *
     * @return a int betwen one and six.
     */
    public int throwDice() {
        return gm.throwDice();
    }

    /**
     * Message to indicate that you fell into a snake
     *
     * @param x Symbol of the current player
     */
    public void showMsgSnake(char x) {
        if (gm.getBooleanSnake()) {
            System.out.println("El jugador " + x + " ha caido en una serpiente, desciende casillas");
        }
    }

    /**
     * Message to indicate that you fell on a ladder
     *
     * @param x Symbol of the current player
     */
    public void showMsgLadder(char x) {
        if (gm.getBooleaLadder()) {
            System.out.println("El jugador " + x + " ha caido en una escalera asciende casillas");
        }
    }

    /**
     * Message to indicate that you have won the game.
     *
     * @param x Winning player symbol
     */
    public void showMsgFinish(Player x) {
        if (gm.getBooleanFinishGame()) {
            System.out.println("El jugador " + x.getSymbol() + " ha ganado el juego, con " + x.getAmountPlay() + " turnos");
        }
    }

    /**
     * Boolean to determine if the menu should be shown again
     *
     * @return boolean true or false
     */
    public boolean showMenuAgain() {
        return gm.getBooleaLadder();
    }

    /**
     * Display the matrix to start the game.
     */
    public void startGame() {
        String st = sc.nextLine();
        if (st.equals("")) {
            Matrix.setCentinela(false);
            soutMatrix();
        }
    }

    /**
     * Methods to enable game functionalities
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void playTheGame() throws IOException, ClassNotFoundException {
        String st = sc.nextLine();
        if (st.equals("")) {
            int dice = throwDice();
            Player py = getPlayer();
            if (moveGame(py, dice)) {
                System.out.println("Por favor ingresa un nombre de usuario para que estes en el podium");
                String nickname = sc.nextLine();
                gm.askPlayer(py, nickname, row, col, getParams(params));
                gm.addBinary(py);
                gm = new Game();
                Matrix.setCentinela(true);
                startProgram();
            } else {
                System.out.println("El jugador " + py.getSymbol() + " lanzo y obtuvo el puntaje " + dice);
                soutMatrix();
                playTheGame();
            }
        } else if (st.equalsIgnoreCase("num")) {
            caseNum();
            playTheGame();
        } else if (st.equalsIgnoreCase("simul")) {
            simul(true);
            startProgram();
        } else if (st.equalsIgnoreCase("menu")) {
            gm = new Game();
            startProgram();
        }
    }

    /**
     * Option to simulate the game and get the quick winner.
     *
     * @param out changes the sentinel to determine when the simul is terminated
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void simul(boolean out) throws IOException, ClassNotFoundException {
        if (out) {
            int dice = throwDice();
            Player py = getPlayer();
            if (moveGame(py, dice)) {
                System.out.println("Por favor ingresa un nombre de usuario para que estes en el podium");
                String nickname = sc.nextLine();
                gm.askPlayer(py, nickname, row, col, getParams(params));
                gm.addBinary(py);
                gm = new Game();
                Matrix.setCentinela(true);
            } else {
                System.out.println("El jugador " + py.getSymbol() + " lanzo y obtuvo el puntaje " + dice);
                soutMatrix();
                simul(true);
            }
        }
    }

    /**
     * Displays the matrix in different ways.
     */
    public void caseNum() {
        Matrix.setCentinela(true);
        soutMatrix();
        Matrix.setCentinela(false);
        sc.nextLine();
        soutMatrix();
    }

    /**
     * Gets the array of parameters as a string
     *
     * @param param Parameter setting
     * @return
     */
    public String getParams(String[] param) {
        String msg = params[1] + " " + params[1] + " " + params[2] + " " + params[3];
        return msg;
    }

    /**
     * show the instruccions of the Game.
     */
    
    public void showInstruccion() {
        String msg = "";
        msg += "--------------------------------------------\n";
        msg += "[1] Al inicio del juego se te pedira unos parametros para iniciar\n";
        msg += "el juego, debes de ingresar una cadena con la siguiente condicion\n";
        msg += "m (# filas) n (# columnas) s (# serpientes) e (# escaleras) p (# jugadores)\n";
        msg += "--------------------------------------------\n";
        msg += "[2] recuerda que el numero de serpientes y escaleras no debe de ser superior\n";
        msg += "al numero de casillas\n";
        msg += "--------------------------------------------\n";
        msg += "[3] Una vez en el juego, para lanzar el dado se debe de dar un salto de linea (Enter)\n";
        msg += "Si se escribe la palabra num, se debe de mostrar el tablero con las serpientes, escaleras y casillas\n";
        msg += "Si se escribe la palabra simul, el juego se jugara de manera automatica\n";
        msg += "Si se escribe la palabra menu, el juego se terminara y nadie ganara la partida\n";
        msg += "--------------------------------------------\n";
        msg += "[4] La tabla de posiciones estara dada por la siguiente formula m*n*cantidad de turnos\n";
        msg += "Se organizara de mayor a menor, donde el puntaje más alto sera el primero\n";
        msg += "--------------------------------------------";
        System.out.println(msg);
    }
}
