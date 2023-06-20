/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.Console;

/**
 * Esta clase funciona para organizar carreras de caballos
 *
 * @version 1.0
 * @author LuisF
 */
public class Race {
    private ArrayList<Jockey> jockeys;
    private ArrayList<Bet> bets;
    private ArrayList<Person> players;
    private ArrayList<Jockey> results;
    private boolean startRace;

    public ArrayList<Jockey> getResults() {
        return results;
    }

    public void setResults(ArrayList<Jockey> results) {
        this.results = results;
    }
    /**
     * Construcor de la carrera, inicializa las listas
     */
    public Race(){
        jockeys = new ArrayList();
        bets = new ArrayList();
        players = new ArrayList();
    }
    public void addPlayer(Person player){
        players.add(player);
    }
    public void addJockey(Jockey jockey){
        jockeys.add(jockey);
    }
    public void addBet(Person person, int amount, Jockey jockey){
        Bet bet = new Bet(person, jockey, amount, this);
        bets.add(bet);
    }
    /**
     * Calcula el premio de los ganadores
     * @return Premio de los ganadores
     */
    public int calculatePrize(){
        int winners = 0;
        int amount = 0;
        for(int i = 0; i < players.size(); i++){
            amount += players.get(i).getCurrentBet().getAmount();
        }
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getCurrentBet().getJockey().getName().equals(results.get(i).getName())){
                winners ++;
            }
        }
        return (int)(amount/winners);
    }
    public Race(String winner){
        results = new ArrayList();
        results.add(new Jockey(winner));
    }
    /**
     * Inicia la carrera y genera los hilos de los caballos
     */
    public void startRace(){
        results = new ArrayList();
        startRace = false;
        for(int i = 0; i < jockeys.size(); i++){
            ThreadHorse newHorse = new ThreadHorse(jockeys.get(i).getHorse(),this);
            Thread t = new Thread(newHorse);
            t.start();
        }
        
        startRace = true;
        while(!checkRaceOver()){
            for(int i =0; i < jockeys.size(); i ++){
                Console.printHorse(jockeys.get(i));
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Race.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }
    private boolean checkRaceOver(){
        if(results.size() == jockeys.size()){
            return true;
        }
        return false;
    }
    public boolean isStartRace() {
        return startRace;
    }

    public void setStartRace(boolean startRace) {
        this.startRace = startRace;
    }

    public ArrayList<Jockey> getJockeys() {
        return jockeys;
    }

    public void setJockeys(ArrayList<Jockey> jockeys) {
        this.jockeys = jockeys;
    }

    public ArrayList<Bet> getBets() {
        return bets;
    }

    public void setBets(ArrayList<Bet> bets) {
        this.bets = bets;
    }

    public ArrayList<Person> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Person> players) {
        this.players = players;
    }
    
    
    
}
