package ui;

import java.io.IOException;

/**
 * Main
 * @author Gabriel Suarez
 * @author David Montaño
 */

public class Main {
    
    /**
     * Metodh main to initialize the program Snake and Ladder
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException 
     */

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MenuMain.welcome();
        MenuMain m;
        m = new MenuMain();
        m.startProgram();
    }
}
