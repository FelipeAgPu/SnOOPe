package Aplicacion;

import javax.swing.*;
import java.io.Serializable;

public class Division extends PowerUp implements Serializable {
    public Division(SnOOPe partida) {
        super(partida);
        this.tipo = "Division";
        this.img = "./images/division.png";
    }
    /**
     * @see Aplicacion.PowerUp.esUsada
     */
    @Override
    public void esUsada(Snake snake){
        if (partida.getSnakes().size()>1) {
            for (Snake snakeActual : partida.getSnakes()) {
                if (!(snakeActual == snake)) {
                    int times = snakeActual.getSnake().size() / 2;
                    for (int i = 0; i < times; i++) {
                        snakeActual.decrecer();
                    }
                }
            }
        }
        else {
            int times = snake.getSnake().size()/2;
            for (int i = 0; i < times; i++) {
                snake.decrecer();
            }
        }
    }
}
