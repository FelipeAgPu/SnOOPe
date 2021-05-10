package Aplicacion;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SnOOPe {
    private int nFilas, nColumnas;
    private ArrayList<Snake> snakes;
    private ArrayList<Color> colores;
    private Fruta[] frutas;
    private Timer[] timers;
    private String[] tiposFruta = {"Normal", "Arcoiris", "Dulce", "Veneno"};

    /**
     * Constructor de la clase SnOOPe
     * @param rows Cantidad de Filas
     * @param columns Cantidad de Columnas
     */
    public SnOOPe(int rows, int columns){
        this.nFilas = rows;
        this.nColumnas = columns;
        this.snakes = new ArrayList<>();
        this.colores = new ArrayList<>();
        colores.add(Color.RED);
        colores.add(Color.BLUE);
        colores.add(Color.CYAN);
        colores.add(Color.ORANGE);
        colores.add(Color.GREEN);

        this.timers = new Timer[2];

        this.frutas = new Fruta[2];
        frutas[0] = crearFrutaAleatoria();
        frutas[1] = crearFrutaAleatoria();

        getTimers()[0] = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                getFrutas()[0] = crearFrutaAleatoria();
            }
        };
        getTimers()[0].schedule(task, 8000, 8000);

        getTimers()[1] = new Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                getFrutas()[1] = crearFrutaAleatoria();
            }
        };
        getTimers()[1].schedule(task1, 8000,8000);

    }

    /**
     * Método que inicia una partida single player
     * @param nombre Nombre del jugador
     * @param colorHead Color de la cabeza
     * @param colorBody Color del cuerpo
     */
    public void jugar(String nombre, Color colorHead, Color colorBody){
        snakes.add(new Jugador(nombre, colorBody, colorHead, this));
        colores.add(colorHead);
        colores.add(colorBody);
    }

    /**
     * Método que crea una fruta de un tipo aleatorio
     * @return Fruta de cualquier tipo
     */
    public Fruta crearFrutaAleatoria(){
        Fruta ans = null;
        Random rn = new Random();
        int x = rn.nextInt(4);
        switch (tiposFruta[x]){
            case "Normal":
                ans = new Normal(this);
                break;
            case "Arcoiris":
                ans = new Arcoiris(this);
                break;
            case "Dulce":
                ans = new Dulce(this);
                break;
            case "Veneno":
                ans = new Veneno(this);
                break;
        }
        return ans;
    }


    public int getnFilas() {
        return nFilas;
    }

    public int getnColumnas() {
        return nColumnas;
    }

    public ArrayList<Snake> getSnakes() {
        return snakes;
    }

    public void addSnakes(Snake snake) {
        this.snakes.add(snake);
    }

    public ArrayList<Color> getColores() {
        return colores;
    }

    public Fruta[] getFrutas() {
        return frutas;
    }

    public Timer[] getTimers() {
        return timers;
    }


}
