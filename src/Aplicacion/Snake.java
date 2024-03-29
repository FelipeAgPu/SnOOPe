package Aplicacion;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Snake implements Serializable {
    protected String nombre;
    protected ArrayList<Integer[]> snake;
    protected Color colorBody, colorHead;
    protected String direccion="UP";
    protected String nuevaDireccion="RIGHT";
    protected SnOOPe partida;
    protected int puntos;
    protected PowerUp poder;
    protected boolean speed = false;
    protected boolean efecto = false;
    protected boolean noSpeed =false;
    protected boolean lanzoFuego = false;
    protected PowerUp fuego;
    public boolean FuegoVsBloque=false;
    public int newSize;

    protected boolean isCongelada = false;
    protected transient Timer timerCongelada;

    /**
     * Constructor de una serpiente de longitud 3
     * @param colorBody Color del cuerpo
     * @param colorHead Color de la cabeza
     * @param partida Partida a la que se va a agregar
     */
    public Snake(String nombre, Color colorBody, Color colorHead, SnOOPe partida){
        this.colorBody = colorBody;
        this.colorHead = colorHead;
        this.partida = partida;
        this.nombre = nombre;

        this.snake = new ArrayList<>();
        if(partida.getSnakes().size()==1){
            Integer[] a = {29, 19};
            Integer[] b = {28, 19};
            Integer[] c = {27, 19};
            this.snake.add(a);
            this.snake.add(b);
            this.snake.add(c);
            this.nuevaDireccion="LEFT";
        }else {
            Integer[] a = {0, 0};
            Integer[] b = {1, 0};
            Integer[] c = {2, 0};
            this.snake.add(a);
            this.snake.add(b);
            this.snake.add(c);
        }
    }

    /**
     * Método que hace que la serpiente avance
     * @throws SnOOPeException Si la serpiente se estrella
     */
    public void avanzar() throws SnOOPeException {

    }

    /**
     * Método que hace que la serpiente coma
     * @throws SnOOPeException Si la serpiente se come a sí misma o come veneno
     */
    public void comer() throws SnOOPeException {
        try{
            boolean isFruta = false;
            for (int i = 0; i < 2; i++) {
                if (snake.get(snake.size() - 1)[0].equals(partida.getFrutas()[i].getCoordenadas()[0]) && snake.get(snake.size() - 1)[1].equals(partida.getFrutas()[i].getCoordenadas()[1])){
                    if (efecto){
                        efecto = false;
                    }
                    else {
                        partida.getFrutas()[i].esComida(this);
                    }

                    isFruta = true;
                    partida.getFrutas()[i] = partida.crearFrutaAleatoria();
                    partida.getTimers()[i].cancel();
                    partida.getTimers()[i] = new Timer();
                    int finalI = i;
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            partida.getFrutas()[finalI] = partida.crearFrutaAleatoria();
                        }
                    };
                    if (partida.getFrutas()[i].getTipo().equals("Golden")){
                        partida.getTimers()[i].schedule(task, 3000, 3000);
                    }else {
                        partida.getTimers()[i].schedule(task, 8000, 8000);
                    }
                }
                if (newSize==snake.size()){
                    FuegoVsBloque=false;
                }
                for (int j = 0; j<snake.size()-1;j++){
                    if(snake.get(snake.size() - 1)[0].equals(snake.get(j)[0]) && snake.get(snake.size() - 1)[1].equals(snake.get(j)[1]) && !isFruta && !FuegoVsBloque){
                        throw new SnOOPeException(SnOOPeException.GAME_OVER_SUICIDIO);
                    }
                }

                for (Integer[] coor: partida.getBloques()){
                    if (snake.get(snake.size() - 1)[0].equals(coor[0]) && snake.get(snake.size() - 1)[1].equals(coor[1])){
                        throw new SnOOPeException(SnOOPeException.GAME_OVER_WALL);
                    }
                }
                if (partida.getSnakes().size()>1){
                    for (Snake snakes:partida.getSnakes()) {
                        if (partida.getSnakes().get(0)==this){
                            Snake snakeEnemiga = partida.getSnakes().get(1);
                            for (int j = 0; j<snakeEnemiga.getSnake().size();j++){
                                if(snake.get(snake.size() - 1)[0].equals(snakeEnemiga.getSnake().get(j)[0]) && snake.get(snake.size() - 1)[1].equals(snakeEnemiga.getSnake().get(j)[1]) && !isFruta){
                                    throw new SnOOPeException(SnOOPeException.LA_MATO);
                                }
                            }
                        }
                        else {
                            Snake snakeEnemiga = partida.getSnakes().get(0);
                            for (int j = 0; j < snakeEnemiga.getSnake().size(); j++) {
                                if(snake.get(snake.size() - 1)[0].equals(snakeEnemiga.getSnake().get(j)[0]) && snake.get(snake.size() - 1)[1].equals(snakeEnemiga.getSnake().get(j)[1]) && !isFruta){
                                    throw new SnOOPeException(SnOOPeException.LA_MATO);
                                }
                            }
                        }
                    }
                }

            }
            puntos = snake.size();
            newSize++;
        }catch (IndexOutOfBoundsException | NullPointerException e){
            System.out.println("F");
        }

    }
    /**
     * Metodo que recoge los powerUps en el tablero
     */
    public void recoge() {
        if (snake.get(snake.size() - 1)[0].equals(partida.getPowerUp().getCoordenadas()[0]) && snake.get(snake.size() - 1)[1].equals(partida.getPowerUp().getCoordenadas()[1])){
            this.poder = partida.getPowerUp();
            Timer timerpow=partida.getTimerPower();
            timerpow.cancel();
            PowerUp aux = new PowerUp(partida);
            aux.setCoordenadas(this.poder.getCoordenadas());
            partida.setPowerUp(aux);
        }

        puntos = snake.size();
    }

    /**
     * Metodo que usa el powerUp de la serpiente y genera otra aleatoriamente en un rango de 8 segundos
     */
    public void usaPowerUp(){
        if (poder != null){
            this.poder.esUsada(this);
            partida.setTimerPower(new Timer());
            TimerTask taskPower = new TimerTask() {
                @Override
                public void run() {
                    partida.setPowerUp(partida.crearPowerUpAleatorio());
                }
            };
            Random rand = new Random();
            int x = rand.nextInt(9);
            partida.getTimerPower().schedule(taskPower,x*1000);
            puntos = snake.size();
            poder = null;
        }
    }

    /**
     * Método que cambia la dirección de la serpiente
     * @param direccion String de la dirección
     */
    public void cambiarDireccion(String direccion){
        if (direccion.equals("UP") && !this.direccion.equals("DOWN")){
            this.nuevaDireccion = direccion;
        }else if (direccion.equals("DOWN") && !this.direccion.equals("UP")){
            this.nuevaDireccion = direccion;
        }else if (direccion.equals("RIGHT") && !this.direccion.equals("LEFT")){
            this.nuevaDireccion = direccion;
        }else if(direccion.equals("LEFT") && !this.direccion.equals("RIGHT")){
            this.nuevaDireccion = direccion;
        }
    }

    /**
     * Método que actualiza la dirección de la serpiente
     */
    public void igualarDireccion(){
        this.direccion = this.nuevaDireccion;
    }

    //Métodos refactorización

    /**
     * Método que aumenta el tamaño de la serpiente
     * @param coordenadas Coordenas de la posicióna a aumentar
     */
    public void crecer(Integer[] coordenadas){
        this.snake.add(coordenadas);
    }

    /**
     * Método que disminuye el tamañao de la serpiente
     */
    public void decrecer(){
        this.snake.remove(0);
    }

    public void congela(){
        this.isCongelada = true;
        this.timerCongelada = new Timer();
        TimerTask taskCongelada = new TimerTask() {
            @Override
            public void run() {
                isCongelada = false;
            }
        };
        timerCongelada.schedule(taskCongelada, 5000);
    }


    //Getters y Setters

    public ArrayList<Integer[]> getSnake() {
        return snake;
    }

    public Color getColorBody() {
        return colorBody;
    }

    public void setColorBody(Color colorBody) {
        this.colorBody = colorBody;
    }

    public void setColorHead(Color colorHead) {
        this.colorHead = colorHead;
    }

    public Color getColorHead() {
        return colorHead;
    }

    public SnOOPe getPartida() {
        return partida;
    }

    public void setPartida(SnOOPe partida) {
        this.partida = partida;
    }

    public int getPuntos() { return puntos; }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNuevaDireccion() {
        return nuevaDireccion;
    }

    public boolean isLanzoFuego() {
        return lanzoFuego;
    }

    public PowerUp getFuego() {
        return fuego;
    }

    public void setLanzoFuego(boolean lanzoFuego) {
        this.lanzoFuego = lanzoFuego;
    }

    public boolean getSpeed() {
        return speed;
    }

    public void setFuego(PowerUp fuego) {
        this.fuego = fuego;
    }

    public PowerUp getPoder(){return poder;}

    public void setSpeed(boolean speed){
        this.speed = speed;
    }

    public boolean getNoSpeed() {
        return noSpeed;
    }

    public boolean isCongelada() {
        return isCongelada;
    }
}
