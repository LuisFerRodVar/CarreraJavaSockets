/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comunication;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Person;

/**
 * Esta clase funciona para instanciar un hilo que ayuda al servidor a crear
 * hilos nuevos para cada nuevo cliente conectado
 *
 * @version 1.0
 * @author LuisF
 */
public class FlowServer extends Thread {

    private ServerSocket socket;
    private Server server;

    /**
     * Construcotr del hilo
     *
     * @param socket Es el socket del servidor utilizado para recibir nuevas
     * conexiones
     * @param server Es la instancia del servidor para realizar operaciones
     * sobre este
     */
    public FlowServer(ServerSocket socket, Server server) {
        this.socket = socket;
        this.server = server;

    }
    /**
     * Inicia el hilo para agregar nuevas conexiones
     */
    public void run() {
        while (true) {
            try {
                Socket nsfd = socket.accept();
                DataInputStream FlujoLectura = new DataInputStream(new BufferedInputStream(nsfd.getInputStream()));
                String name = FlujoLectura.readUTF();
                Person currentPerson = null;
                if (checkPerson(name)) {

                    for (int i = 0; i < server.personsDataBase.size(); i++) {
                        if (server.personsDataBase.get(i).getName().equals(name)) {

                            currentPerson = server.personsDataBase.get(i);
                            currentPerson.setSocket(nsfd);
                            break;
                        }
                    }

                } else {
                    currentPerson = new Person(name, nsfd);
                    server.personsDataBase.add(currentPerson);

                }
                server.race.addPlayer(currentPerson);
                server.conectedPersons.add(currentPerson);

                System.out.println("Conexion aceptada de: " + name);

                FlowForClient flujo = new FlowForClient(nsfd, currentPerson);
                Thread t = new Thread(flujo);
                t.start();
            } catch (IOException ex) {
                Logger.getLogger(FlowServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean checkPerson(String name) {
        synchronized (server.personsDataBase) {
            for (int i = 0; i < Server.getInstance().personsDataBase.size(); i++) {
                if (Server.getInstance().personsDataBase.get(i).getName().equals(name)) {
                    return true;
                }
            }
            return false;

        }
    }
}
