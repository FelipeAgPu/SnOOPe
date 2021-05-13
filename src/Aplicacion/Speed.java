package Aplicacion;

import javax.swing.*;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class Speed extends PowerUp implements Serializable {
    public Speed(SnOOPe partida) {
        super(partida);
        this.tipo = "Speed";
        this.img ="./images/velocidad.png";
    }

    @Override
    public void esUsada(Snake snake){
        snake.speed = true;
    }
}
