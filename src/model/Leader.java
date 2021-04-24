package model;

public class Leader {

    private Nodo firstL;
    private Nodo lastL;
    private char idL;

    public Leader(Nodo firstL, Nodo lastL, char idL) {
        this.firstL = firstL;
        this.lastL = lastL;
        this.idL = idL;
    }

    public Nodo getFirstL() {
        return firstL;
    }

    public void setFirstL(Nodo firstL) {
        this.firstL = firstL;
    }

    public Nodo getLastL() {
        return lastL;
    }

    public void setLastL(Nodo lastL) {
        this.lastL = lastL;
    }

    public char getIdL() {
        return idL;
    }

    public void setIdL(char idL) {
        this.idL = idL;
    }
}
