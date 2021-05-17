package Aplicacion;

import Presentacion.Movimiento;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SnOOPe implements Serializable {
    private int nFilas, nColumnas;
    private ArrayList<Snake> snakes;
    private ArrayList<Color> colores;
    private ArrayList<Integer[]> bloques;
    private Fruta[] frutas;
    private transient Timer[] timers;
    private transient Timer timerPower;
    private PowerUp powerUp;
    private ArrayList<String> tiposFruta;
    private ArrayList<String> tiposPowerUps;


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
        this.bloques = new ArrayList<>();
        colores.add(Color.RED);
        colores.add(Color.BLUE);
        colores.add(Color.CYAN);
        colores.add(Color.ORANGE);
        colores.add(Color.GREEN);
    }

    /**
     * Método que inicia una partida single player
     * @param nombre Nombre del jugador
     * @param colorHead Color de la cabeza
     * @param colorBody Color del cuerpo
     */
    public void jugar(String nombre, Color colorHead, Color colorBody,ArrayList<String> frutas,ArrayList<String> powerUps){
        this.tiposFruta = frutas;
        this.tiposPowerUps = powerUps;
        snakes.add(new Jugador(nombre, colorBody, colorHead, this));
        colores.add(colorHead);
        colores.add(colorBody);
        this.timers = new Timer[2];

        this.frutas = new Fruta[2];
        this.frutas[0] = crearFrutaAleatoria();
        this.frutas[1] = crearFrutaAleatoria();

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

        this.powerUp = crearPowerUpAleatorio();

        this.timerPower = new Timer();
    }

    /**
     * Método que inicia una partida single player
     * @param nombre Nombre del jugador
     * @param colorHead Color de la cabeza
     * @param colorBody Color del cuerpo
     */
    public void jugar(String nombre, Color colorHead, Color colorBody, String nombre2, Color colorHead2, Color colorBody2,ArrayList<String> frutas,ArrayList<String> powerUps){
        this.tiposFruta = frutas;
        this.tiposPowerUps = powerUps;

        //Creación Snakes
        snakes.add(new Jugador(nombre, colorBody, colorHead, this));
        colores.add(colorHead);
        colores.add(colorBody);

        snakes.add(new Jugador(nombre2, colorBody2, colorHead2, this));
        colores.add(colorHead2);
        colores.add(colorBody2);

        this.timers = new Timer[2];

        this.frutas = new Fruta[2];
        this.frutas[0] = crearFrutaAleatoria();
        this.frutas[1] = crearFrutaAleatoria();

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

        this.powerUp = crearPowerUpAleatorio();

        this.timerPower = new Timer();
    }

    /**
     * Método que crea una fruta de un tipo aleatorio
     * @return Fruta de cualquier tipo
     */
    public Fruta crearFrutaAleatoria(){
        Fruta ans = null;
        Random rn = new Random();
        int x = rn.nextInt(tiposFruta.size());
        switch (tiposFruta.get(x)){
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

    /**
     * Método que crea una fruta de un tipo aleatorio
     * @return Fruta de cualquier tipo
     */
    public PowerUp crearPowerUpAleatorio(){
        PowerUp ans = null;
        Random rn = new Random();
        int x = rn.nextInt(tiposPowerUps.size());
        switch (tiposPowerUps.get(x)){
            case "Speed":
                ans = new Speed(this);
                break;
            case "Bloque":
                ans = new Bloque(this);
                break;
            case "Division":
                ans = new Division(this);
                break;
            case "Fuego":
                ans = new Estrella(this);
                break;
            case "Lupa":
                ans = new Lupa(this);
                break;
        }
        return ans;
    }

    //Persistencia

    /**
     * Método que guarda en un archivo la informacíon del programa
     * @param file Archivo a guardar
     * @throws SnOOPeException
     */
    public void guardar(File file) throws SnOOPeException{
        if (!file.getAbsolutePath().endsWith(".dat")){
            throw new SnOOPeException(SnOOPeException.ERROR_TIPO_GUARDAR);
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()));

            out.writeObject(this);

            out.close();
        }catch (IOException e){
            e.printStackTrace();
            throw new SnOOPeException(SnOOPeException.ERROR_GUARDAR);
        }
    }

    /**
     * Método que abre un archivo y toma la informacíon del archivo para reanudar un juego guardado
     * @param archivo Archivo a abrir
     * @throws SnOOPeException
     */
    public static SnOOPe abrir(File archivo) throws SnOOPeException {
        if (!archivo.getAbsolutePath().endsWith(".dat")){
            throw new SnOOPeException(SnOOPeException.ERROR_TIPO_ABRIR);
        }
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo.getAbsolutePath()));

            SnOOPe snoope = (SnOOPe) in.readObject();

            in.close();


            return snoope;
        }catch (IOException | ClassNotFoundException e){
            throw new SnOOPeException(SnOOPeException.ERROR_ABRIR);
        }
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

    public ArrayList<Color> getColores() {
        return colores;
    }

    public Fruta[] getFrutas() {
        return frutas;
    }

    public Timer[] getTimers() {
        return timers;
    }

    public void setTimers() {
        this.timers = new Timer[2];
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

        this.timerPower = new Timer();
        TimerTask taskPower = new TimerTask() {
            @Override
            public void run() {
                setPowerUp(crearPowerUpAleatorio());
            }
        };
        Random rand = new Random();
        int x = rand.nextInt(9);
        timerPower.schedule(taskPower,x*1000);

    }

    public void printInfo(){
        System.out.println("Frutas");
        for (int i = 0; i < 2; i++) {
            System.out.println(frutas[i].getTipo() + ": " + frutas[i].coordenadasToString());
        }

        System.out.println("Snake");
        for (Snake snake: this.snakes){
            for (Integer[] coord: snake.getSnake()){
                System.out.println( coord[0] + " " + coord[1]   );
            }
        }

    }

    public PowerUp getPowerUp() {
        return powerUp;
    }

    public Timer getTimerPower() {
        return timerPower;
    }

    public void setTimerPower(Timer timerPower) {
        this.timerPower = timerPower;
    }

    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    /**
     * Método que agrega un bloque del powerUp Bloque
     * @param bloque coordenadas del bloque
     */
    public void addBloque(Integer[] bloque){
        this.bloques.add(bloque);
    }

    public ArrayList<Integer[]> getBloques() {
        return bloques;
    }

    public static void main(String[] args) {
        Aplicacion.Movimiento movimiento;
        Thread hilo;

        SnOOPe snnope = new SnOOPe(20, 30);

        //Creación arreglo de frutas
        ArrayList<String> frutas = new ArrayList<>();
        frutas.add("Normal");
        frutas.add("Veneno");
        frutas.add("Dulce");
        frutas.add("Arcoiris");

        //Creación arreglo de powerups
        ArrayList<String> powerUps = new ArrayList<>();
        powerUps.add("Speed");
        powerUps.add("Tijeras");
        powerUps.add("Fuego");
        powerUps.add("Bloque");
        powerUps.add("Lupa");

        snnope.jugar("Snake", new Color(0x6E6EB1), new Color(0x5FB85F), frutas, powerUps );

        movimiento = new Aplicacion.Movimiento(snnope.getSnakes().get(0));
        hilo = new Thread(movimiento);
        hilo.start();



    }
}
