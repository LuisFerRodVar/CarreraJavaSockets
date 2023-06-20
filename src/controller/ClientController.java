/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import comunication.Client;
import view.Console;

/**
 * Esta clase inicia el cliente y conecta todas las partes necesarias del
 * programa que un cliente pueda necesitar
 *
 * @version 1.0
 * @author LuisF
 */
public class ClientController {

    /**
     * Inicia el cliente, conecta la parte grafica con el modelo y el cliente
     *
     * @param args
     */
    public static void main(String[] args) {
        Client client = new Client(Console.askUser("Digite su nombre de usuario:"));

        boolean flag = false;
        while (!flag) {
            int valor = menuClient();
            switch (valor) {
                case 1:

                    int horse = deployHorses(client);
                    int amount = getAmount();
                    client.sendData(horse - 1 + ":" + amount);
                    Console.printMessage("Apuesta registrada");
                    flag = true;
                    break;
                case 2:
                    deployData(client);

                    break;
                default:
                    Console.printMessage("Digite un valor valido");

            }
        }
        Console.printMessage("Espere a que termine la carrera");
        Console.printMessage(client.getData());

    }

    private static int getAmount() {
        int amount = 0;
        try {
            amount = Integer.valueOf(Console.askUser("Digite el monto de la apuesta"));
        } catch (Exception e) {
            Console.printMessage("Digite un valor valido");
            return getAmount();
        }

        if (amount <= 0) {
            Console.printMessage("Digite un valor positivo");
            return getAmount();
        }
        return amount;
    }

    private static int deployHorses(Client client) {
        client.sendData("0");
        String message = "Jinete" + "\t" + "\t" + "Caballo";
        Console.printMessage(message);

        String[] horses = client.getData().split("\n");

        String[] currentHorse;
        for (int i = 0; i < horses.length; i++) {
            currentHorse = horses[i].split(":");
            message = (i + 1) + ". " + currentHorse[0] + "\t" + "\t" + currentHorse[3];
            Console.printMessage(message);
        }
        int value = 0;
        try {
            value = Integer.valueOf(Console.askUser("Digite el nombre del caballo al que desea apostar: "));
        } catch (Exception e) {
            Console.printMessage("Digite un valor valido");
            return deployHorses(client);
        }

        if (value <= 0) {
            Console.printMessage("Digite un valor positivo diferente de 0");
            return deployHorses(client);
        }
        if (value > horses.length) {
            Console.printMessage("Digite un valor dentro del rango");
            return deployHorses(client);
        }
        return value;
    }

    private static void deployData(Client client) {
        client.sendData("0");
        String message = "Nombre" + "\t" + "\t" + "Peso" + "\t" + "\t" + "Experiencia" + "\t" + "\t" + "Caballo";
        Console.printMessage(message);
        String jockeyData = client.getData();
        String[] jockeys = jockeyData.split("\n");
        String[] currentJockey;
        for (int i = 0; i < jockeys.length; i++) {
            currentJockey = jockeys[i].split(":");
            message = currentJockey[0] + "\t" + "\t" + currentJockey[1] + "kg" + "\t" + "\t" + currentJockey[2] + " años" + "\t" + "\t" + "\t" + currentJockey[3];
            Console.printMessage(message);
        }
    }

    private static int menuClient() {

        Console.printMessage("1. Apostar a un jinete");
        Console.printMessage("2. Ver jinetes y sus respectivos caballos");
        try {
            return Integer.valueOf(Console.askUser("Digite la opcion deseada: "));

        } catch (Exception e) {
            Console.printMessage("Debe digitar un valor valido");
            return menuClient();
        }

    }
}
