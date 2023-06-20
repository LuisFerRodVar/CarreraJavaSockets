/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comunication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bet;
import model.Jockey;
import model.Person;
import model.Race;
import view.Console;

/**
 * Esta clase funciona para instanciar hilos que ayuden al servidor a recibir
 * tareas distintas en parelelo
 *
 * @version 1.0
 * @author LuisF
 */
public class FlowForClient extends Thread {

    private Socket socket;
    private Person person;
    private DataOutputStream output;
    private DataInputStream input;

    /**
     * Constructor del hilo
     *
     * @param socket Socket del cliente
     * @param person Persona conectada al servidor
     */
    public FlowForClient(Socket socket, Person person) {
        try {
            this.person = person;
            this.socket = socket;
            this.output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            this.input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(FlowForClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * Inicia el hilo, interpreta los mensajes enviados por el cliente
     */
    public void run() {
        while (true) {
            try {
                String value = input.readUTF();
                if (value.equals("0")) {
                    String message = "";
                    synchronized (Server.getInstance().race.getJockeys()) {
                        ArrayList<Jockey> jockeys = Server.getInstance().race.getJockeys();
                        if (!jockeys.isEmpty()) {
                            message += jockeys.get(0).getName() + ":";
                            message += jockeys.get(0).getWeight() + ":";
                            message += jockeys.get(0).getXp() + ":";
                            message += jockeys.get(0).getHorse().getName() + ":";

                        }
                        for (int i = 1; i < jockeys.size(); i++) {
                            message += "\n";
                            message += jockeys.get(i).getName() + ":";
                            message += jockeys.get(i).getWeight() + ":";
                            message += jockeys.get(i).getXp() + ":";
                            message += jockeys.get(i).getHorse().getName() + ":";
                        }
                    }
                    output.writeUTF(message);
                    output.flush();
                } else {
                    String[] valueAux = value.split(":");
                    int horse = Integer.valueOf(valueAux[0]);
                    int amount = Integer.valueOf(valueAux[1]);
                    synchronized (Server.getInstance().race) {
                        Jockey jockey = Server.getInstance().race.getJockeys().get(horse);

                        Server.getInstance().race.addBet(person, amount, jockey);
                    }
                    Console.printMessage("Recibida una apuesta de: " + person.getName() + " por " + amount);
                    break;
                }
            } catch (IOException ex) {
                Logger.getLogger(FlowForClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e) {
                break;

            }
        }
        synchronized (Server.getInstance().winner) {
            while (!Server.getInstance().winner.equals("%")) {

            }
            Race race = Server.getInstance().race;
            if (race.getResults().get(0).getName().equals(person.getCurrentBet().getJockey().getName())) {
                try {
                    output.writeUTF("Felicidades ha ganado: " + race.calculatePrize());
                    output.flush();
                } catch (IOException ex) {
                    Logger.getLogger(FlowForClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    output.writeUTF("Lo sentimos ha perdido");
                    output.flush();
                } catch (IOException ex) {
                    Logger.getLogger(FlowForClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
