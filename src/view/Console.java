/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;
import model.Jockey;

/**
 * Esta clase funciona para manejar la parte grafica del programa por medio de
 * la consola
 *
 * @version 1.0
 * @author LuisF
 */
public class Console {

    private static int count = 1;
    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    private static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    private static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static Scanner scanner = new Scanner(System.in);
    /**
     * Imprime un mensaje
     * @param message mensaje por imprimir
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }
    /**
     * Pregunta al usuario y devuelve su respuesta
     * @param message La respuesta del usuario
     * @return 
     */
    public static String askUser(String message) {
        printMessage(message);
        return scanner.nextLine();
    }
    /**
     * Imprime un caballo para la carrera de caballos
     * @param jockey Jinete por imprimir
     */
    public static void printHorse(Jockey jockey) {
        for (int i = 0; i < jockey.getHorse().getTravel() / 10; i++) {
            System.out.print(" ");
        }
        switch (jockey.getHorse().getColorId()) {
            case 1:
                System.out.println(ANSI_BLACK_BACKGROUND + jockey.getName() + ANSI_RESET);
                break;
            case 2:
                System.out.println(ANSI_RED_BACKGROUND + jockey.getName() + ANSI_RESET);
                break;
            case 3:
                System.out.println(ANSI_GREEN_BACKGROUND + jockey.getName() + ANSI_RESET);
                break;
            case 4:
                System.out.println(ANSI_YELLOW_BACKGROUND + jockey.getName() + ANSI_RESET);
                break;
            case 5:
                System.out.println(ANSI_BLUE_BACKGROUND + jockey.getName() + ANSI_RESET);
                break;
            case 6:
                System.out.println(ANSI_PURPLE_BACKGROUND + jockey.getName() + ANSI_RESET);
                break;
            case 7:
                System.out.println(ANSI_CYAN_BACKGROUND + jockey.getName() + ANSI_RESET);
                break;
            case 8:
                System.out.println(ANSI_WHITE_BACKGROUND + jockey.getName() + ANSI_RESET);
                break;
        }

    }

}
