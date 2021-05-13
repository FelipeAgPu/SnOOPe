package Aplicacion;

import javax.swing.*;
import java.io.Serializable;

public class Lupa extends PowerUp implements Serializable {
    public Lupa(SnOOPe partida) {
        super(partida);
        this.tipo = "Lupa";
        this.img = "./images/lupa.png";
    }

    @Override
    public void esUsada(Snake snake){
        snake.efecto = true;
    }
}
