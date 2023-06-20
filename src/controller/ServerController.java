/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import comunication.Server;
import java.util.ArrayList;
import model.Horse;
import model.Jockey;
import model.Person;
import model.Race;
import view.Console;

/**
 * Esta clase funciona para controlar el servidor, conectar su parte grafica con
 * el resto del codigo y demas
 *
 * @version 1.0
 * @author LuisF
 */
public class ServerController {

    private static Server server;
    private static ArrayList<String> addedJockeys;
    /**
     * Inicia el servidor
     * @param args 
     */
    public static void main(String[] args) {
        addedJockeys = new ArrayList();
        server = Server.getInstance();
        boolean flag = false;
        Console.printMessage("Bienvenido al sistema de organizacion del hipodromo");
        while (!flag) {
            int entry = showMenu();

            switch (entry) {

                case 1:
                    try {
                    String jockeyName = Console.askUser("Digite el nombre del jinete: ");
                    int jockeyWeight = Integer.valueOf(Console.askUser("Digite el peso del jinete en kilos sin decimales"));
                    int jockeyXp = Integer.valueOf(Console.askUser("Digite los anios de experiencia del jinete"));
                    String horseName = Console.askUser("Digite el nombre del caballo");
                    int horseSpeed = Integer.valueOf(Console.askUser("Digite la velocidad maxima del caballo en km x h sin decimales"));
                    server.addJockey(new Jockey(jockeyName, jockeyWeight, jockeyXp, new Horse(horseName, horseSpeed)));
                    Console.printMessage("El jinete ha sido agregado con exito");
                    break;
                } catch (Exception e) {
                    Console.printMessage("Digite un valor valido");
                    break;
                }

                case 2:
                    ArrayList<Person> persons = server.personsDataBase;
                    for (int i = 0; i < persons.size(); i++) {
                        Console.printMessage(persons.get(i).getName());
                    }
                    break;
                case 3:
                    showJockeysVoid(server.jockeys);
                    break;
                case 4:
                    if (server.jockeys.size() < 2) {
                        Console.printMessage("Debe agregar al menos 2 jinetes para iniciar la carrera");
                    } else {
                        flag = true;
                    }

                    break;
                case 5:
                    Console.printMessage("Esta es una tarea programada del curso de algoritmos y estructuras de datos creada durante el primer semestre del 2022");
                    Console.printMessage("Desarrollada por: Adrian Valverde, Luis Rodriguez y Nancy Lezcano");
                    break;
                case 6:
                    ArrayList<Race> races = server.races;
                    for (int i = 0; i < races.size(); i++) {
                        Console.printMessage("Ganador: " + races.get(i).getResults().get(0).getName());
                    }
                    break;
                case 7:
                    server.exit();
                    return;
                default:
                    Console.printMessage("Digite un valor valido");
            }

        }
        flag = false;
        while (!flag) {
            try {
                int entry = showJockeys(server.jockeys);
                if (entry == 0) {
                    if (server.race.getJockeys().size() < 2) {
                        Console.printMessage("Debe agregar al menos 2 jinetes para iniciar la carrera");
                        continue;
                    }
                    flag = true;
                } else {

                    addedJockeys.add(server.jockeys.get(entry - 1).getName());
                    addJockey(entry - 1);
                    if (addedJockeys.size() == server.jockeys.size()) {
                        flag = true;
                    }
                }
            } catch (Exception e) {
                Console.printMessage("Digite un numero valido entero positivo dentro del rango esquipulado");
            }

        }

        server.startServer();
        flag = false;
        Console.printMessage("Se estan aceptando conexiones");
        Console.askUser("Digite cualquier tecla para continuar y dejar de aceptar conexiones ");
        server.startRace();
        Console.printMessage("Resultados: ");
        for (int i = 0; i < server.race.getResults().size(); i++) {
            Console.printMessage((i + 1) + ". " + server.race.getResults().get(i).getName());

        }
        server.checkWinners();
        Console.askUser("La carrera ha terminado digite cualquier tecla para terminar y guardar los datos");
        server.exit();

    }

    private static boolean addJockey(int index) {
        ArrayList<Jockey> jockeys = server.race.getJockeys();
        for (int i = 0; i < jockeys.size(); i++) {
            if (server.jockeys.get(index).getName().equals(jockeys.get(i).getName())) {
                return false;
            }
        }
        jockeys.add(server.jockeys.get(index));
        return true;
    }

    private static boolean searchInList(String forSearch) {
        for (int i = 0; i < addedJockeys.size(); i++) {
            if (forSearch.equals(addedJockeys.get(i))) {
                return true;
            }
        }
        return false;
    }

    private static int showJockeys(ArrayList<Jockey> jockeys) {
        for (int i = 0; i < jockeys.size(); i++) {
            if (!searchInList(jockeys.get(i).getName())) {
                Console.printMessage((i + 1) + "." + jockeys.get(i).getName());
            }

        }
        return Integer.valueOf(Console.askUser("Digite el numero del jinete que desea agregar a la carrera, digite 0 para iniciar la carrera"));
    }

    private static void showJockeysVoid(ArrayList<Jockey> jockeys) {

        for (int i = 0; i < jockeys.size(); i++) {
            Console.printMessage((i + 1) + "." + jockeys.get(i).getName());
        }

    }

    private static int showMenu() {
        Console.printMessage("Digite la opcion deseada: ");
        String message = "";
        message += "1. Agregar nuevo jinete y su respectivo caballo" + "\n";
        message += "2. Ver usuarios registrados al sistema" + "\n";
        message += "3. Ver jinetes registrados al sistema" + "\n";
        message += "4. Iniciar carrera" + "\n";
        message += "5. Acerca de" + "\n";
        message += "6. Ver registros de carreras anteriores" + "\n";
        message += "7. Salir";
        try {
            return Integer.valueOf(Console.askUser(message));
        } catch (Exception e) {
            Console.printMessage("Digite un valor valido");
            return showMenu();
        }

    }
}
