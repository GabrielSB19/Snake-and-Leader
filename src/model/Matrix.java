package model;

public class Matrix {
    
    private int numRow;
    private int numCol;
    
    private Nodo first;

    public Matrix(int numRow, int numCol) {
        this.numRow = numRow;
        this.numCol = numCol;
        createMatrix();
    }
    
    private void createMatrix (){
        first = new Nodo(0,0);
        createRow(0,0, first);
    }
    
    private void createRow(int i, int j, Nodo firstRow){
        createCol(i, j, firstRow);
    }

    private void createCol(int i, int j, Nodo prev) {
       
    }

}
