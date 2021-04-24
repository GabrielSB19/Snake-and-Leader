package ui;

import model.*;

public class MethodMain {

    Game gm = new Game();

    String[] params = new String[5];

    private int row = 0;
    private int col = 0;
    
    public void params(String param) {
        params = param.split(" ");
    }

    public void newMatrix() {
        row = Integer.parseInt(params[0]);
        col = Integer.parseInt(params[1]);
        System.out.println(gm.newMatrix(row, col));
    }

    public void createSnakes() {
        int ammountSnake = Integer.parseInt(params[2]);
        int maxNods = Integer.parseInt(params[0]) * Integer.parseInt(params[1]);
        gm.createSnakes(row, col, ammountSnake, maxNods);
    }
}
