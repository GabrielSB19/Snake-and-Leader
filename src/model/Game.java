package model;

public class Game {

    private int countSnakes = 0;
    private Matrix matrix;

    public String newMatrix(int row, int col) {
        matrix = new Matrix(row, col);
        return matrix.toStringMatrix();
    }

    public void createSnakes(int row, int col, int ammountSnakes, int maxNods) {
        if (countSnakes < ammountSnakes) {
            int max = (matrix.getNumCol() * matrix.getNumRow());
            int min = (matrix.getNumCol() + 1);
            int snakeFirst = (int) Math.floor(Math.random() * (max - min) + min);
            System.out.println(snakeFirst);
            Nodo nd = matrix.searchNode(snakeFirst);
            System.out.println(nd.getNum());
        }
    }
}
