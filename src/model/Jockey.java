/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Esta clase funciona para instanciar jinetes
 *
 * @version 1.0
 * @author LuisF
 */
public class Jockey {
    private String name;
    private int weight;
    private int xp;
    private Horse horse;
    /**
     * Construcotr de los jinetes
     * @param name Nombre del jinete
     * @param weight Peso del jinete
     * @param xp Experiencia del jinete en anios
     * @param horse Caballo del jinete
     */
    public Jockey(String name, int weight, int xp, Horse horse) {
        this.name = name;
        this.weight = weight;
        this.xp = xp;
        this.horse = horse;
        horse.setJockey(this);
    }
    
    public Jockey(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }
    
}
