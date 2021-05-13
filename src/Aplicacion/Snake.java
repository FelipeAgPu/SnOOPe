package Aplicacion;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Snake implements Serializable {
    protected ArrayList<Integer[]> snake;
    protected Color colorBody, colorHead;
    protected String direccion="UP";
    protected String nuevaDireccion="RIGHT";
    protected SnOOPe partida;
    protected int puntos;
    protected PowerUp poder;
    protected boolean speed = false;
    protected boolean efecto = false;

    /**
     * Constructor de una serpiente de longitud 3
     * @param colorBody Color del cuerpo
     * @param colorHead Color de la cabeza
     * @param partida Partida a la que se va a agregar
     */
    public Snake(Color colorBody, Color colorHead, SnOOPe partida){
        this.colorBody = colorBody;
        this.colorHead = colorHead;
        this.partida = partida;

        this.snake = new ArrayList<>();

        Integer[] a={10,10};
        Integer[] b={10,11};
        Integer[] c={10,12};
        this.snake.add(a);
        this.snake.add(b);
        this.snake.add(c);
        this.puntos = snake.size();
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
                partida.getTimers()[i].schedule(task, 8000, 8000);
            }

            for (int j = 0; j<snake.size()-1;j++){
                if(snake.get(snake.size() - 1)[0].equals(snake.get(j)[0]) && snake.get(snake.size() - 1)[1].equals(snake.get(j)[1]) && !isFruta){
                    throw new SnOOPeException(SnOOPeException.GAME_OVER_SUICIDIO);
                }
            }

            for (Integer[] coor: partida.getBloques()){
                if (snake.get(snake.size() - 1)[0].equals(coor[0]) && snake.get(snake.size() - 1)[1].equals(coor[1])){
                    throw new SnOOPeException(SnOOPeException.GAME_OVER_WALL);
                }
            }
        }
        puntos = snake.size();
    }

    public void recoge() {
        if (snake.get(snake.size() - 1)[0].equals(partida.getPowerUp().getCoordenadas()[0]) && snake.get(snake.size() - 1)[1].equals(partida.getPowerUp().getCoordenadas()[1])){
            this.poder = partida.getPowerUp();
            PowerUp aux = new PowerUp(partida);
            aux.setCoordenadas(this.poder.getCoordenadas());
            partida.setPowerUp(aux);
        }

        puntos = snake.size();
    }

    public void usaPowerUp(){
        if (poder != null){
            this.poder.esUsada(this);
            partida.setPowerUp(partida.crearPowerUpAleatorio());
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

    public ArrayList<Integer[]> getSnake() {
        return snake;
    }

    public Color getColorBody() {
        return colorBody;
    }

    public void setColorBody(Color colorBody) {
        this.colorBody = colorBody;
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

    public int getPuntos() {
        return puntos;
    }

    public String getNombre() {
        return null;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNuevaDireccion() {
        return nuevaDireccion;
    }

    public boolean getSpeed() {
        return speed;
    }

    public PowerUp getPoder(){return poder;}

    public void setSpeed(boolean speed){
        this.speed = speed;
    }
}
