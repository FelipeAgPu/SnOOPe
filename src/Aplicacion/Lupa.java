package Aplicacion;

import javax.swing.*;
import java.io.Serializable;

public class Lupa extends PowerUp implements Serializable {
    public Lupa(SnOOPe partida) {
        super(partida);
        this.tipo = "Lupa";
        this.img = "./images/lupa.png";
    }
    /**
     * @see Aplicacion.PowerUp.esUsada
     */
    @Override
    public void esUsada(Snake snake){
        if (partida.getSnakes().size()>1){
            for (Snake snakes:partida.getSnakes()) {
                if (!(snakes ==snake)){
                    snakes.efecto=true;
                }
            }
        }
        else{
            snake.efecto=true;
        }
    }
}
