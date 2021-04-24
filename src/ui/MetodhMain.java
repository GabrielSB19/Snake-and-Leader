package ui;

import model.*;

public class MetodhMain {
    
    Game gm = new Game();
    
    String [] params = new String [5];
    
    public void params(String param){
        params = param.split(" ");
    }
    
    public void newMatrix(){
        int row = Integer.parseInt(params[0]);
        int col = Integer.parseInt(params[1]);
        System.out.println(gm.newMatrix(row, col));
    }
}
