/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comunication;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Jockey;
import model.Person;
import model.Race;
import utilities.FileManager;

/**
 * Esta clase funciona como el servidor del programa, realiza gran parte de las
 * operaciones con los clientes, se apoya de varios hilos y utiliza un patron de
 * disenio singleton
 *
 * @version 1.0
 * @author LuisF
 */
public class Server {

    public ServerSocket socket;
    public ArrayList<Person> personsDataBase;
    public ArrayList<Person> conectedPersons;
    public Race race;
    public ArrayList<Race> races;
    public ArrayList<Jockey> jockeys;
    public FlowServer flow;
    public String winner;
    private static Server singleton = new Server();

    /**
     * Singleton
     *
     * @return Retorna la unica instancia del servidor
     */
    public static Server getInstance() {
        return singleton;
    }

    /**
     * Aniade un jinete al servidor
     *
     * @param newJockey nuevo jinete por aniadir
     */
    public void addJockey(Jockey newJockey) {
        jockeys.add(newJockey);
    }

    /**
     * Registra un jinete a la carrera
     *
     * @param indexJockeys es el indice del respectivo jinete
     */
    public void registerJockeysToRace(ArrayList<Integer> indexJockeys) {
        for (int i = 0; i < indexJockeys.size(); i++) {
            race.addJockey(jockeys.get(i));
        }

    }

    /**
     * Registra una persona al servidor
     *
     * @param person es la persona por registrar
     */
    public void registerPerson(Person person) {
        conectedPersons.add(person);
        personsDataBase.add(person);
    }

    /**
     * Constructor del servidor, inicializa las listas con datos
     */
    private Server() {
        winner = " ";
        personsDataBase = FileManager.loadPersons();
        conectedPersons = new ArrayList();
        races = FileManager.loadPreviousRaces();
        race = new Race();
        
        jockeys = FileManager.loadJockeys();

    }

    /**
     * Cierra el servidor y guarda sus datos en directorios
     */
    public void exit() {
        FileManager.saveJockeys(jockeys);
        if(race.getResults()!= null){
            races.add(race);
        }
        FileManager.saveRaces(races);
        FileManager.savePersons(personsDataBase);
        System.exit(0);
    }

    /**
     * Inicia la carrera
     */
    public void startRace() {
        race.startRace();
    }

    /**
     * Verifica los ganadores de la carrera y les envia sus premios
     */
    public void checkWinners() {
        int amount = 0;
        int winners = 0;
        for (int i = 0; i < conectedPersons.size(); i++) {
            amount += conectedPersons.get(i).getCurrentBet().getAmount();
            if (conectedPersons.get(i).getCurrentBet().getJockey().getName().equals(race.getResults().get(0).getName())) {
                winners++;
            }
        }
        int prize = (int) (amount / winners);
        for (int i = 0; i < conectedPersons.size(); i++) {
            if (conectedPersons.get(i).getCurrentBet().getJockey().getName().equals(race.getResults().get(0).getName())) {
                try {
                    DataOutputStream output = new DataOutputStream(new BufferedOutputStream(conectedPersons.get(i).getSocket().getOutputStream()));
                    output.writeUTF("Felicidades ha ganado: " + prize);
                    output.flush();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    DataOutputStream output = new DataOutputStream(new BufferedOutputStream(conectedPersons.get(i).getSocket().getOutputStream()));
                    output.writeUTF("Lo sentimos no ha ganado");
                    output.flush();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
    /**
     * Arranca las conexiones de nuevos clientes
     */
    public void startServer() {
        try {
            conectedPersons = new ArrayList();

            socket = new ServerSocket(8080);
            flow = new FlowServer(socket, this);
            Thread t = new Thread(flow);
            t.start();

        } catch (IOException ex) {
            System.out.println("Comunicación rechazada." + ex);
            System.exit(1);
        }

    }
    /**
     * Deja de aceptar nuevos clientes
     */
    public void rejectNewConnections() {
        flow.interrupt();
    }

}
