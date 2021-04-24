package model;

public class Matrix {

    private int numRow;
    private int numCol;

    private Nodo first;

    public Matrix(int numRow, int numCol) {
        this.numRow = numRow;
        this.numCol = numCol;
        createMatrix();
        changeBox(first);
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

    private void createMatrix() {
        first = new Nodo(0, 0, 0);
        createRow(0, 0, first);
    }

    private void createRow(int i, int j, Nodo firstRow) {
        createCol(i, j + 1, firstRow, firstRow.getUp());
        if (i + 1 < numRow) {
            Nodo downFirstRow = new Nodo(i + 1, j, 0);
            downFirstRow.setUp(firstRow);
            firstRow.setDown(downFirstRow);
            createRow(i + 1, j, downFirstRow);
        }
    }

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

    public String toStringMatrix() {
        String msg;
        msg = toStringRow(first);
        return msg;
    }

    private String toStringRow(Nodo firstRow) {
        String msg = "";
        if (firstRow != null) {
            msg += toStringCol(firstRow) + "\n";
            msg += toStringRow(firstRow.getDown());
        }

        return msg;
    }

    private String toStringCol(Nodo current) {
        String msg = "";
        if (current != null) {
            msg += current.toString();
            msg += toStringCol(current.getNext());
        }
        return msg;
    }

    public void changeBox(Nodo first) {
        changeBoxFirstRow(first);
    }

    private void changeBoxFirstRow(Nodo firstRow) {
        if (firstRow.getDown() != null) {
            changeBoxFirstRow(firstRow.getDown());
        } else {
            firstRow.setNum(1);
            changeBoxNextRow(firstRow);
        }
    }

    private void changeBoxNextRow(Nodo nextRow) {
        if (nextRow.getNext() != null) {
            nextRow.getNext().setNum(nextRow.getNum() + 1);
            changeBoxNextRow(nextRow.getNext());
        } else if (nextRow.getPrev().getUp() != null) {
            nextRow.getUp().setNum(nextRow.getNum() + 1);
            changeBoxPrevRow(nextRow.getUp());
        }
    }

    private void changeBoxPrevRow(Nodo prevRow) {
        if (prevRow.getPrev() != null) {
            prevRow.getPrev().setNum(prevRow.getNum() + 1);
            changeBoxPrevRow(prevRow.getPrev());
        } else if (prevRow.getNext().getUp() != null) {
            prevRow.getUp().setNum(prevRow.getNum() + 1);
            changeBoxNextRow(prevRow.getUp());
        }
    }

    public Nodo searchNode(int n) {
        if (first.getNum() == n) {
            return first;
        } else {
            return searchNodeRight(first.getNext(), n);
        }
    }

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

    public int getMaxNumL(Nodo nd) {
        Nodo node1 = getNodeRight(nd.getDown());
        Nodo node2 = getNodeLeft(nd.getDown());
        if (node1.getNum() > node2.getNum()) {
            return node1.getNum();
        } else {
            return node2.getNum();
        }
    }

    private Nodo getNodeRight(Nodo nd) {
        if (nd.getNext() != null) {
            return getNodeRight(nd.getNext());
        } else {
            return nd;
        }
    }

    private Nodo getNodeLeft(Nodo nd) {
        if (nd.getPrev() != null) {
            return getNodeLeft(nd.getPrev());
        } else {
            return nd;
        }
    }

    public void createSnake(Nodo max, Nodo min) {
        max.setSnake(min);
    }
}
