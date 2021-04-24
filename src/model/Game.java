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
            int maxNumF = (matrix.getNumCol() * matrix.getNumRow());
            int minNumF = (matrix.getNumCol() + 1);
            int snakeFirst = (int) Math.floor(Math.random() * (maxNumF - minNumF) + minNumF);
            Nodo maxNd = matrix.searchNode(snakeFirst);
            System.out.println("Max: " + maxNd.getNum());
            int maxNumL = matrix.getMaxNumL(maxNd) + 1;
            int minNumL = 2;
            int snakeLast = (int) Math.floor(Math.random() * (maxNumL - minNumL) + minNumL);
            Nodo minNd = matrix.searchNode(snakeLast);
            System.out.println("Min: " + minNd.getNum());
            matrix.createSnake(maxNd, minNd);
            System.out.println(maxNd.getSnake().getNum());
            System.out.println(matrix.toStringMatrix());
        }
    }
}
