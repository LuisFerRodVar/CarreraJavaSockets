/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Esta clase funciona para instanciar apuestas
 *
 * @version 1.0
 * @author LuisF
 */
public class Bet {
    private Person person;
    private Jockey jockey;
    private int amount;
    private Race race;
    /**
     * Constructor de las apuestas
     * @param person Persona que apuesta
     * @param jockey Jinete por el cual la persona apuesta
     * @param amount Monto de la apuesta
     * @param race  Carrera en la que se aposto
     */
    public Bet(Person person, Jockey jockey, int amount, Race race) {
        this.person = person;
        this.jockey = jockey;
        this.amount = amount;
        this.race = race;
        person.setCurrentBet(this);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Jockey getJockey() {
        return jockey;
    }

    public void setJockey(Jockey jockey) {
        this.jockey = jockey;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
    
}
