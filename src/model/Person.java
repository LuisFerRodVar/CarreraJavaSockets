/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.net.Socket;
import java.util.ArrayList;

/**
 * Esta clase funciona para instanciar personas que funcionan como clientes
 *
 * @version 1.0
 * @author Nancy,Adrian,Luis
 */
public class Person {
    private Socket socket;
    private String name;
    private Bet currentBet;
    private ArrayList<Bet> bets;
    
    public Person(String name){
        this.name = name;
    }
    /**
     * Constructor de las personas
     * @param name Nombre de la persona
     * @param nsfd Socket de la persona
     */
    public Person(String name, Socket nsfd) {
        this.socket = nsfd;
        this.name = name;
    }
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bet getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(Bet currentBet) {
        this.currentBet = currentBet;
    }

    public ArrayList<Bet> getBets() {
        return bets;
    }

    public void setBets(ArrayList<Bet> bets) {
        this.bets = bets;
    }
    
    
}
