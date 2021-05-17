package Aplicacion;

import javax.swing.*;
import java.io.Serializable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Speed extends PowerUp implements Serializable {
    boolean noVel;
    String[] images;

    public Speed(SnOOPe partida) {
        super(partida);
        this.tipo = "Speed";
        noVel=false;
        images = new String[]{"./images/velocidad.png", "./images/NoVelocidad.png"};
        Random rand = new Random();
        int x =rand.nextInt(2);
        if(images[x]=="./images/NoVelocidad.png") {
            noVel = true;
        }
        this.img = images[x];
    }

    /**
     * @see Aplicacion.PowerUp.esUsada
     */
    @Override
    public void esUsada(Snake snake){
        if(partida.getSnakes().size()>1) {
            for (Snake snakes : partida.getSnakes()) {
                if (!(snakes == snake) && noVel) {
                    snakes.speed = true;
                    snakes.noSpeed = noVel;
                }
            }
        }
        else{
            snake.speed = true;
            snake.noSpeed = noVel;
        }

    }
}
