package model;

public class Matrix {

    private static boolean centinela = true;
    private int numRow;
    private int numCol;
    private int charOrderAscii = 65;
    private int intOrderAscii = 1;
    private Nodo first;

    /**
     * Builder Matrix
     * @param numRow number of rows
     * @param numCol number of columns
     */
    
    public Matrix(int numRow, int numCol) {
        this.numRow = numRow;
        this.numCol = numCol;
        createMatrix();
        changeBox(first);
    }

    public static void setCentinela(boolean b) {
        centinela = b;
    }

    public Nodo getFirst() {
        return first;
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

    /**
     * Create the matrix
     */
    
    private void createMatrix() {
        first = new Nodo(0, 0, 0);
        createRow(0, 0, first);
    }

    /**
     * Create the rows of the matrix. 
     * @param i position new row
     * @param j position new col
     * @param firstRow first box
     */
    
    private void createRow(int i, int j, Nodo firstRow) {
        createCol(i, j + 1, firstRow, firstRow.getUp());
        if (i + 1 < numRow) {
            Nodo downFirstRow = new Nodo(i + 1, j, 0);
            downFirstRow.setUp(firstRow);
            firstRow.setDown(downFirstRow);
            createRow(i + 1, j, downFirstRow);
        }
    }

    /**
     * Create the cols of the matrix
     * @param i position new row
     * @param j position new col
     * @param prev box initial to create col
     * @param rowPrev first Box
     */
    
    private void createCol(int i, int j, Nodo prev, Nodo rowPrev) {
        if (j < numCol) {
            Nodo current = new Nodo(i, j, 0);
            current.setPrev(prev);
            prev.setNext(current);

            if (rowPrev != null) {
                rowPrev = rowPrev.getNext();
                current.setUp(rowPrev);
                rowPrev.setDown(current);
            }
            createCol(i, j + 1, current, rowPrev);
        }
    }

    /**
     * Show the matrix
     * @return matrix
     */
    
    public String toStringMatrix() {
        String msg;
        msg = toStringRow(first);
        return msg;
    }

    /**
     * Shoe the rows of the matrix
     * @param firstRow first box
     * @return show of rows
     */
    
    private String toStringRow(Nodo firstRow) {
        String msg = "";
        if (firstRow != null) {
            msg += toStringCol(firstRow) + "\n";
            msg += toStringRow(firstRow.getDown());
        }

        return msg;
    }
    
    /**
     * Show the cols of the matrix
     * @param current first box
     * @return show the cols
     */
    
    private String toStringCol(Nodo current) {
        String msg = "";
        if (current != null) {
            if (centinela) {
                msg += current.toString();
            } else {
                msg += current.toStringGame();
            }
            msg += toStringCol(current.getNext());
        }
        return msg;
    }
    
    /**
     * Enumeration of the boxes.
     * @param first first box
     */
    
    public void changeBox(Nodo first) {
        changeBoxFirstRow(first);
    }
    
    /**
     * Change the num's boxes of the row
     * @param firstRow first box.
     */
    
    private void changeBoxFirstRow(Nodo firstRow) {
        if (firstRow.getDown() != null) {
            changeBoxFirstRow(firstRow.getDown());
        } else {
            firstRow.setNum(1);
            changeBoxNextRow(firstRow);
        }
    }
    
    /**
     * Enumeration of the next boxes
     * @param nextRow box next
     */
    
    private void changeBoxNextRow(Nodo nextRow) {
        if (nextRow.getNext() != null) {
            nextRow.getNext().setNum(nextRow.getNum() + 1);
            changeBoxNextRow(nextRow.getNext());
        } else if (nextRow.getPrev().getUp() != null) {
            nextRow.getUp().setNum(nextRow.getNum() + 1);
            changeBoxPrevRow(nextRow.getUp());
        }
    }
    
    /**
     * Enumeration of the previous boxes
     * @param prevRow  box prev
     */

    private void changeBoxPrevRow(Nodo prevRow) {
        if (prevRow.getPrev() != null) {
            prevRow.getPrev().setNum(prevRow.getNum() + 1);
            changeBoxPrevRow(prevRow.getPrev());
        } else if (prevRow.getNext().getUp() != null) {
            prevRow.getUp().setNum(prevRow.getNum() + 1);
            changeBoxNextRow(prevRow.getUp());
        }
    }

    /**
     * Search any box
     * @param n num of box
     * @return box
     */
    
    public Nodo searchNode(int n) {
        if (first.getNum() == n) {
            return first;
        } else {
            return searchNodeRight(first.getNext(), n);
        }
    }

    /**
     * Recursive Search the nodo in the right
     * @param nd first box
     * @param n num to search
     * @return nodo
     */
    
    private Nodo searchNodeRight(Nodo nd, int n) {
        if (nd != null) {
            if (nd.getNum() == n) {
                return nd;
            } else {
                if (nd.getNext() != null) {
                    return searchNodeRight(nd.getNext(), n);
                } else if (nd.getDown() != null) {
                    return searchNodeLeft(nd.getDown(), n);
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    /**
     * Recursive Search the nodo in the left
     * @param nd first box
     * @param n num to search
     * @return nodo
     */
    
    private Nodo searchNodeLeft(Nodo nd, int n) {
        if (nd != null) {
            if (nd.getNum() == n) {
                return nd;
            } else {
                if (nd.getPrev() != null) {
                    return searchNodeLeft(nd.getPrev(), n);
                } else if (nd.getDown() != null) {
                    return searchNodeRight(nd.getDown(), n);
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    /**
     * Gets the box where the end of the snake can be located.
     * @param nd nodo of init the snake
     * @return nodo final
     */
    
    public int getMaxNumLSnake(Nodo nd) {
        Nodo node1 = getNodeRight(nd.getDown());
        Nodo node2 = getNodeLeft(nd.getDown());
        if (node1.getNum() > node2.getNum()) {
            return node1.getNum();
        } else {
            return node2.getNum();
        }
    }

    /**
     * Gets the box where the end of the ladder can be located.
     * @param nd nodo of init the ladder
     * @return nodo final
     */
    
    public int getMaxNumLLeader(Nodo nd) {
        Nodo node1 = getNodeRight(nd.getUp());
        Nodo node2 = getNodeLeft(nd.getUp());
        if (node1.getNum() > node2.getNum()) {
            return node2.getNum();
        } else {
            return node1.getNum();
        }
    }

    /**
     * Recursive search to right
     * @param nd box nodo first
     * @return  limit box
     */
    
    private Nodo getNodeRight(Nodo nd) {
        if (nd.getNext() != null) {
            return getNodeRight(nd.getNext());
        } else {
            return nd;
        }
    }

    /**
     * Recursive search to left
     * @param nd box nodo first
     * @return limit box
     */
    
    private Nodo getNodeLeft(Nodo nd) {
        if (nd.getPrev() != null) {
            return getNodeLeft(nd.getPrev());
        } else {
            return nd;
        }
    }

    
    /**
     * Create the snake in the matrix
     * @param max end the snake
     * @param min int the sknae
     */
    
    public void createSnake(Nodo max, Nodo min) {
        Snake snake = new Snake(max, min, (char) charOrderAscii++);
        max.setSnake(snake);
        min.setSnake(snake);
    }

    /**
     * Create the ladder in the matrix
     * @param max end the ladder
     * @param min init the ladder
     */
    
    public void createLeader(Nodo max, Nodo min) {
        Leader leader = new Leader(max, min, intOrderAscii++);
        max.setStair(leader);
        min.setStair(leader);
    }
}
