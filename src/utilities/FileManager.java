/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Horse;
import model.Jockey;
import model.Person;
import model.Race;

/**
 * Esta clase funciona para administrar la carga y el guardado de datos
 *
 * @version 1.0
 * @author LuisF
 */
public class FileManager {

    /**
     * Carga las personas
     *
     * @return Una lista de personas que utilizaron el programa con anterioridad
     */
    public static ArrayList<Person> loadPersons() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<Person> persons = new ArrayList();
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("Users.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String line;
            while ((line = br.readLine()) != null) {
                persons.add(new Person(line));
            }
        } catch (Exception e) {
            try {
                FileWriter fichero = null;
                PrintWriter pw = null;
                fichero = new FileWriter("Users.txt");
                fichero.close();
                e.printStackTrace();
            } catch (IOException ex) {

            }
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return persons;
    }

    /**
     * Guarda las personas que han utilizado el programa durante la sesion o
     * bien en sesiones anteriores
     *
     * @param persons Lista de personas por guardar
     */
    public static void savePersons(ArrayList<Person> persons) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("Users.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < persons.size(); i++) {
                pw.println(persons.get(i).getName());

            }
            fichero.close(); //CERRAR EL ARCHIVO

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Carga las carreras anteriores
     * @return Retorna una lista con el ganador de las anteriores carreras
     */
    public static ArrayList<Race> loadPreviousRaces() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<Race> races = new ArrayList();
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("Races.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String line;
            while ((line = br.readLine()) != null) {
                races.add(new Race(line));
            }
        } catch (Exception e) {
            try {
                FileWriter fichero = null;
                PrintWriter pw = null;
                fichero = new FileWriter("Races.txt");
                fichero.close();
                e.printStackTrace();
            } catch (IOException ex) {

            }
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return races;
    }
    /**
     * Guarda las carreras
     * @param races Es la lista de carreras por guardar
     */
    public static void saveRaces(ArrayList<Race> races) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("Races.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < races.size(); i++) {
                pw.println(races.get(i).getResults().get(0).getName());

            }
            fichero.close(); //CERRAR EL ARCHIVO

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Carga los jinetes en el programa
     * @return Una lista de los jinetes que estaban contenidos en el archivo
     */
    public static ArrayList<Jockey> loadJockeys() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<Jockey> jockeys = new ArrayList();
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("Jockeys.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(":");
                String name = data[0];
                int weight = Integer.valueOf(data[1]);
                int xp = Integer.valueOf(data[2]);
                String horse = data[3];
                int horseSpeed = Integer.valueOf(data[4]);
                jockeys.add(new Jockey(name, weight, xp, new Horse(horse, horseSpeed)));
            }
        } catch (Exception e) {
            try {
                FileWriter fichero = null;
                PrintWriter pw = null;
                fichero = new FileWriter("Jockeys.txt");
                fichero.close();
                e.printStackTrace();
            } catch (IOException ex) {

            }
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return jockeys;
    }
    /**
     * Guarda los jinetes del programa
     * @param jockeys Lista de jinetes por guardar en un archivo
     */
    public static void saveJockeys(ArrayList<Jockey> jockeys) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("Jockeys.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < jockeys.size(); i++) {
                pw.print(jockeys.get(i).getName() + ":");
                pw.print(jockeys.get(i).getWeight() + ":");
                pw.print(jockeys.get(i).getXp() + ":");
                pw.print(jockeys.get(i).getHorse().getName() + ":");
                pw.println(jockeys.get(i).getHorse().getMaxSpeed() + ":");
            }
            fichero.close(); //CERRAR EL ARCHIVO

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
