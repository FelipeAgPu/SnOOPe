package Aplicacion;

import javax.swing.*;
import java.io.Serializable;

public class Division extends PowerUp implements Serializable {
    public Division(SnOOPe partida) {
        super(partida);
        this.tipo = "Division";
        this.img = "./images/division.png";
    }

    @Override
    public void esUsada(Snake snake){
        int times = snake.getSnake().size()/2;
        for (int i = 0; i < times; i++) {
            snake.snake.remove(0);
        }
    }
}
