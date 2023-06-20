/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase funciona para instanciar hilos que ayudan a simular la carrera de
 * caballos
 *
 * @version 1.0
 * @author LuisF
 */
public class ThreadHorse extends Thread {

    private Horse horse;
    private Race race;
    /**
     * Constructor del hilo
     * @param horse Caballo representado
     * @param race La carrera a la que pertenece el caballo
     */
    public ThreadHorse(Horse horse, Race race) {
        this.horse = horse;
        this.race = race;

    }
    /**
     * Inicia el hilo
     */
    public void run() {

        while (horse.getTravel() < 1000) {
            try {
                int weight = horse.getJockey().getWeight() - horse.getJockey().getXp();
                int speed = horse.getMaxSpeed() - (int) (weight * 0.10);
                int randomNum = (int) (Math.random() * (10 - 1));
                horse.setTravel(horse.getTravel() + (speed + randomNum));
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadHorse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        synchronized (race.getResults()) {
            race.getResults().add(horse.getJockey());
        }
    }

}
