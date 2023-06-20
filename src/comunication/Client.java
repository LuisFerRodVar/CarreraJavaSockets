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
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.Console;

/**
 * Esta clase funciona para instanciar clientes y operar con el servidor
 * @version 1.0
 * @author LuisF
 */
public class Client {

    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;
    /**
     * Constructor del cliente
     * @param user nombre del cliente
     */
    public Client(String user) {
        try {
            socket = new Socket("localhost", 8080);
            output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            output.writeUTF(user);
            output.flush();
        } catch (UnknownHostException uhe) {
            System.out.println("No hay ninguna carrera organizada ni en progreso");
            System.exit(1);
        } catch (IOException ioe) {
            System.out.println("No hay ninguna carrera organizada ni en progreso");
            System.exit(1);
        }
    }
    /**
     * Envia datos al servidor por medio de sockets
     * @param data dato enviado al servidor
     */
    public void sendData(String data) {
        try {
            output.writeUTF(data);
            output.flush();

        } catch (IOException ex) {
            Logger.getLogger(Client.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Recibe datos del servidor
     * @return Retorna los datos recibidos del servidor
     */
    public String getData() {
        try {
            return input.readUTF();

        } catch (IOException ex) {
            Logger.getLogger(Client.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
