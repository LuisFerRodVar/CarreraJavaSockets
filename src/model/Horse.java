/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
/**
 * Esta clase funciona para instanciar caballos de carreras
 *
 * @version 1.0
 * @author LuisF
 */
public class Horse {
    private String name;
    private int maxSpeed;
    private int travel;
    private Jockey jockey;
    private int colorId;
    private static int globalColor = 1;

    public Jockey getJockey() {
        return jockey;
    }

    public void setJockey(Jockey jockey) {
        this.jockey = jockey;
    }
    
    public int getTravel() {
        return travel;
    }

    public void setTravel(int travel) {
        this.travel = travel;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    /**
     * Constructor de los caballos
     * @param name Nombre del caballo
     * @param maxSpeed Velocidad maxima del caballo
     */
    public Horse(String name, int maxSpeed) {
        this.colorId = globalColor;
        globalColor ++;
        if(globalColor == 9){
            globalColor = 1;
        }
        this.travel = 0;
        this.name = name;
        this.maxSpeed = maxSpeed;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
    
    
       
}
