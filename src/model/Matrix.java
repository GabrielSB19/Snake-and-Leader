package model;

public class Matrix {
    
    private int numRow;
    private int numCol;
    private int num = 1;
    
    private Nodo first;

    public Matrix(int numRow, int numCol) {
        this.numRow = numRow;
        this.numCol = numCol;
        createMatrix();
    }

    public int getNumRow() {
        return numRow;
    }

    public void setNumRow(int numRow) {
        this.numRow = numRow;
    }

    public int getNumCol() {
        return numCol;
    }

    public void setNumCol(int numCol) {
        this.numCol = numCol;
    }
    
    private void createMatrix (){
        first = new Nodo(0,0, num++);
        createRow(0,0, first);
    }
    
    private void createRow(int i, int j, Nodo firstRow){
        createCol(i, j+1, firstRow);
        if (i+1 < numRow) {
            Nodo downFirstRow = new Nodo(i+1, j, num++);
            downFirstRow.setUp(firstRow);
            firstRow.setDown(downFirstRow);
            createRow(i+1, j, downFirstRow);
        }
    }

    private void createCol(int i, int j, Nodo prev) {
        if(j < numCol){
            Nodo current = new Nodo(i, j, num++);
            current.setPrev(prev);
            prev.setNext(current);
            createCol(i, j+1, current);
        }
    }
    
    public String toString(){
        String msg;
        msg = toStringRow(first);
        return msg;
    }

    private String toStringRow(Nodo firstRow) {
        String msg = "";
        if (firstRow != null) {
            msg += toStringCol(firstRow)+"\n";
            msg += toStringRow(firstRow.getDown());
        }

        return msg;
    }

    private String toStringCol(Nodo current) {
        String msg = "";
        if(current != null){
            msg += current.toString();
            msg += toStringCol(current.getNext());
        }
        return msg;
    }
}
