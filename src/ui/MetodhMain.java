package ui;

import model.*;

public class MetodhMain {
    
    String [] params = new String [5];
    
    public void params(String param){
        params = param.split(" ");
    }
    
    public void newMatrix(){
        Matrix mx = new Matrix(Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        System.out.println(mx.toStringMatrix());
    }
}
