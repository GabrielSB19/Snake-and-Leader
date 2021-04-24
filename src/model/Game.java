package model;

public class Game {
    
    public String newMatrix(int row, int col){
        Matrix mx = new Matrix(row, col);
        return mx.toStringMatrix();
    }

}
