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

    public void newMatrix() {
        row = Integer.parseInt(params[0]);
        col = Integer.parseInt(params[1]);
        System.out.println(gm.newMatrix(row, col));
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
        } catch (Exception e) {
            gm.createPlayerSymb(params[4]);
        }
    }
}
