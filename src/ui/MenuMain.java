package ui;

import model.*;

import java.util.Scanner;

public class MenuMain {

    private Scanner sc = new Scanner(System.in);
    private MethodMain mm = new MethodMain();

    public static void welcome() {
        System.out.println("-----------------------------------");
        System.out.println("Bienvenido a escalares y serpientes");
        System.out.println("-----------------------------------");
    }

    public void mainMenu() {
        System.out.println("Que deseas hacer?");
        System.out.println("[1] Jugar");
        System.out.println("[2] Ver Puntuaciones");
        System.out.println("[3] Instrucciones");
        System.out.println("[4] Salir");
    }

    public int readOption() {
        int choice = Integer.parseInt(sc.nextLine());
        return choice;
    }

    public void doOperation(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Por favor ingresa los parametros del juego en una cadena");
                mm.params(sc.nextLine());
                mm.newMatrix();
                mm.createSnakes();
                break;
            case 2:
                System.out.println("Binary");
                break;
            case 3:
                System.out.println("Instruccion");
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Por favor selecciona una opcion correcta");
                break;
        }
    }

    @SuppressWarnings("InfiniteRecursion")
    public void startProgram() {
        mainMenu();
        int option = readOption();
        doOperation(option);
        startProgram();
    }
}
