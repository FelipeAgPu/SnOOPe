package Aplicacion;

import javax.swing.*;
import java.io.Serializable;

public class Arcoiris extends Fruta implements Serializable {
    /**
     * Constructor fruta Arcoiris
     * @param partida Partida a la se va a agregar
     */
    public Arcoiris(SnOOPe partida) {
        super(partida);
        this.tipo = "Arcoiris";
        this.img = "./images/arcoiris.png";
    }

    /**
     * @See Aplicacion.Fruta.esComida
     */
    @Override
    public void esComida(Snake snake){
        for (int i = 0; i < 3; i++) {
            snake.crecer(getCoordenadas());
        }
    }
}
