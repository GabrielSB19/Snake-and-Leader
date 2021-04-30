package ui;

import model.*;

public class MethodMain {

    Game gm = new Game();
    String[] params = new String[5];
    private int row = 0;
    private int col = 0;

    public void params(String param) {
        gm.setParam(param);
        params = param.split(" ");
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

    public Player getPlayer() {
        return gm.getSymbolPlayer();
    }

    public void newMatrix() {
        row = Integer.parseInt(params[0]);
        col = Integer.parseInt(params[1]);
        gm.newMatrix(row, col);
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
            System.out.println("El jugador " + x.getSymbol() + " ha ganado el juego, con " + x.getTurn() + " turnos");
        }
    }

    public boolean showMenuAgain() {
        return gm.getBooleaLadder();
    }
}
