package Aplicacion;

import java.awt.*;
import java.util.ArrayList;

public class SnOOPe {
    private int nFilas, nColumnas;
    public ArrayList<Color> colores;
    private ArrayList<Snake> snakes;

    public SnOOPe(int rows, int columns){
        this.nFilas = rows;
        this.nColumnas = columns;
    }

    public void jugar(String nombre, Color colorHead, Color colorBody){
        snakes.add(new Jugador(colorBody, colorHead, this));
        colores.add(colorHead);
        colores.add(colorBody);
    }

    /*
    public void jugar(String nombre1, boolean isIa1, String nombre2, boolean isIa2){

    }*/

    public int getnFilas() {
        return nFilas;
    }

    public void setnFilas(int nFilas) {
        this.nFilas = nFilas;
    }

    public int getnColumnas() {
        return nColumnas;
    }

    public void setnColumnas(int nColumnas) {
        this.nColumnas = nColumnas;
    }

    public ArrayList<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(ArrayList<Snake> snakes) {
        this.snakes = snakes;
    }
}
