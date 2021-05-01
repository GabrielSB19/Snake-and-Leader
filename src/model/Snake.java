package model;

import java.io.Serializable;

public class Snake implements Serializable{
    
    private static final long serialVersionUID = 1;
    
    private Nodo firstS;
    private Nodo lastS;
    private char idS;

    public Snake(Nodo firstS, Nodo lastS, char idS) {
        this.firstS = firstS;
        this.lastS = lastS;
        this.idS = idS;
    }

    public Nodo getFirstS() {
        return firstS;
    }

    public void setFirstS(Nodo firstS) {
        this.firstS = firstS;
    }

    public Nodo getLastS() {
        return lastS;
    }

    public void setLastS(Nodo lastS) {
        this.lastS = lastS;
    }

    public char getIdS() {
        return idS;
    }

    public void setIdS(char idS) {
        this.idS = idS;
    }
}
