package Aplicacion;

import javax.swing.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Estrella extends PowerUp implements Serializable {
    public String direccion;
    protected Integer[] coord;

    public Estrella(SnOOPe partida) {
        super(partida);
        this.tipo = "Fuego";
        this.img ="./images/fuego.png";
        this.direccion=null;
        this.coord= new Integer[2];
    }

    @Override
    public void esUsada(Snake snake){
        this.direccion=snake.getDireccion();
        this.coord[0]=snake.getSnake().get(snake.getSnake().size()-1)[0];
        this.coord[1]=snake.getSnake().get(snake.getSnake().size()-1)[1];
        switch (direccion){
            case "UP":
                coord[1]--;
                break;
            case "DOWN":
                coord[1]++;
                break;
            case "RIGHT":
                coord[0]++;
                break;
            case "LEFT":
                coord[0]--;
                break;
        }
        snake.setLanzoFuego(true);
        snake.setFuego(this);
    }

    @Override
    public void avanzar(Snake snake) {
        switch (direccion){
            case "UP":
                coord[1]--;
                break;
            case "DOWN":
                coord[1]++;
                break;
            case "RIGHT":
                coord[0]++;
                break;
            case "LEFT":
                coord[0]--;
                break;
        }
        verificaComestible(snake);
        if (partida.getSnakes().size()>1){
            verificaSnake(snake);
        }

        if (coord[0]>= partida.getnColumnas()){
            coord[0]=null;
            snake.setLanzoFuego(false);
        }else if (coord[0]<0){
            coord[0]=null;
            snake.setLanzoFuego(false);
        }
        if (coord[1]>= partida.getnFilas()){
            coord[1]=null;
            snake.setLanzoFuego(false);
        }else if (coord[1]<0){
            coord[1]=null;
            snake.setLanzoFuego(false);
        }
    }

    @Override
    public Integer[] getCoord() {
        return coord;
    }

    private void verificaSnake(Snake snake){
        for (int i = 0; i < 2; i++) {
            //Si la serpiente no es la del parametro
            if (partida.getSnakes().get(i) != snake){
                //Por cada coordenada de la serpiente 2
                for (int j = 0; j < partida.getSnakes().get(i).getSnake().size(); j++){
                    //Si la coordenanada de la serpiente y la del fuego son iguales
                    if (partida.getSnakes().get(i).getSnake().get(j)[0].equals(coord[0]) && partida.getSnakes().get(i).getSnake().get(j)[1].equals(coord[1])){
                        for (int times = j; times>=0; times--){
                            partida.getSnakes().get(i).getSnake().remove(0);
                        }
                        snake.setLanzoFuego(false);
                        snake.setFuego(null);
                    }
                }
            }
        }
    }

    private void verificaComestible(Snake snake){
        boolean destruyoAlgo = false;
        for (int i = 0; i < 2; i++) {
            if(this.coord[0].equals(snake.getPartida().getFrutas()[i].getCoordenadas()[0])&&this.coord[1].equals(snake.getPartida().getFrutas()[i].getCoordenadas()[1])){
                snake.getPartida().getFrutas()[i]=snake.getPartida().crearFrutaAleatoria();
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
                destruyoAlgo = true;
            }
        }
        if(this.coord[0].equals(snake.getPartida().getPowerUp().getCoordenadas()[0])&&this.coord[1].equals(snake.getPartida().getPowerUp().getCoordenadas()[1])){
           snake.getPartida().setPowerUp(snake.getPartida().crearPowerUpAleatorio());
            partida.getTimerPower().cancel();
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
           destruyoAlgo=true;
        }
        for (int i = 0; i < snake.getPartida().getBloques().size(); i++) {
            if (this.coord[0].equals(snake.getPartida().getBloques().get(i)[0])&&this.coord[1].equals(snake.getPartida().getBloques().get(i)[1])){
                snake.getPartida().getBloques().remove(i);
                snake.FuegoVsBloque=true;
                snake.newSize = snake.getSnake().size();
                for (int j = 0; j < 5; j++) {
                    snake.crecer(snake.getSnake().get(snake.getSnake().size()-1));
                }
                destruyoAlgo=true;
            }
        }
        if (destruyoAlgo){
            snake.setLanzoFuego(false);
            snake.setFuego(null);
        }
    }
}
