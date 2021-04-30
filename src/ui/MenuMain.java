package ui;

import model.*;

import java.util.Scanner;

public class MenuMain {

    private Scanner sc = new Scanner(System.in);
    private Game gm = new Game();

    String[] params = new String[5];
    private int row = 0;
    private int col = 0;

    public static void welcome() {
        System.out.println("--------------------------------------------");
        System.out.println("--------------- Bienvenido A ---------------");
        System.out.println("---------- Escalares y Serpientes ----------");
        System.out.println("--------------------------------------------");
    }

    public void mainMenu() {
        System.out.println("Que deseas hacer?");
        System.out.println("[1] Jugar");
        System.out.println("[2] Ver Puntuaciones");
        System.out.println("[3] Instrucciones");
        System.out.println("[4] Salir");
        System.out.println("--------------------------------------------");
    }

    public int readOption() {
        int choice = Integer.parseInt(sc.nextLine());
        return choice;
    }

    public void doOperation(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Por favor ingresa los parametros del juego en una cadena");
                params(sc.nextLine());
                functionsGame();
                startGame();
                playTheGame();
                break;
            case 2:
                System.out.println("Binary");
                break;
            case 3:
                System.out.println("Instruccion");
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Por favor selecciona una opcion correcta");
                break;
        }
    }

    @SuppressWarnings("InfiniteRecursion")
    public void startProgram() {
        mainMenu();
        int option = readOption();
        doOperation(option);
        startProgram();
    }

    public void params(String param) {
        gm.setParam(param);
        params = param.split(" ");
    }

    public void functionsGame() {
        newMatrix();
        createSnakes();
        createLeader();
        createPlayers();
        soutMatrix();
    }

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

    public void createSnakes() {
        int ammountSnake = Integer.parseInt(params[2]);
        gm.createSnakes(row, col, ammountSnake);
        gm.setCountSnakes(0);
    }

    public void createLeader() {
        int amountLeader = Integer.parseInt(params[3]);
        gm.createLeader(row, col, amountLeader);
        gm.setCountLeader(0);
    }

    public Player getPlayer() {
        return gm.getSymbolPlayer();
    }

    public void createPlayers() {
        try {
            int ammountPlayers = Integer.parseInt(params[4]);
            gm.createPlayers(ammountPlayers);
            gm.setMaxTurns(ammountPlayers + 1);
        } catch (Exception e) {
            gm.createPlayerSymb(params[4]);
            gm.setMaxTurns(params[4].length() + 1);
        }
    }

    public void newMatrix() {
        row = Integer.parseInt(params[0]);
        col = Integer.parseInt(params[1]);
        gm.newMatrix(row, col);
    }

    public void soutMatrix() {
        System.out.println(gm.soutMatrix());
    }

    public int throwDice() {
        return gm.throwDice();
    }

    public void showMsgSnake(char x) {
        if (gm.getBooleanSnake()) {
            System.out.println("El jugador " + x + " ha caido en una serpiente, desciende casillas");
        }
    }

    public void showMsgLadder(char x) {
        if (gm.getBooleaLadder()) {
            System.out.println("El jugador " + x + " ha caido en una escalera asciende casillas");
        }
    }

    public void showMsgFinish(Player x) {
        if (gm.getBooleanFinishGame()) {
            System.out.println("El jugador " + x.getSymbol() + " ha ganado el juego, con " + x.getAmountPlay() + " turnos");
        }
    }

    public boolean showMenuAgain() {
        return gm.getBooleaLadder();
    }

    public void startGame() {
        String st = sc.nextLine();
        if (st.equals("")) {
            Matrix.setCentinela(false);
            soutMatrix();
        }
    }

    public void playTheGame() {
        String st = sc.nextLine();
        if (st.equals("")) {
            int dice = throwDice();
            Player py = getPlayer();
            if (moveGame(py, dice)) {
                System.out.println("Por favor ingresa un nombre de usuario para que estes en el podium");
                String nickname = sc.nextLine();
                gm.askPlayer(py, nickname, row, col);
                System.out.println(py.getNickName()+"Sapa");
                System.out.println(py.getScore()+"Gedionda");
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
            System.out.println("Yes");
        } else if (st.equalsIgnoreCase("Menu")) {
            startProgram();
        }
    }

    public void caseNum() {
        Matrix.setCentinela(true);
        soutMatrix();
        Matrix.setCentinela(false);
        sc.nextLine();
        soutMatrix();
    }
}
