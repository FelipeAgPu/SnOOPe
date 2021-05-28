package Aplicacion;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Normal extends Fruta implements Serializable {
    /**
     * Constructor fruta Arcoiris
     * @param partida Partida a la se va a agregar
     */
    public Normal(SnOOPe partida) {
        super(partida);
        this.tipo = "Normal";
        this.img = "./images/normal.png";
    }

    /**
     * @See Aplicacion.Fruta.esComida
     */
    @Override
    public void esComida(Snake snake){
        if (snake.getColorHead() == this.color || snake.getColorBody() == this.color){
            snake.crecer(getCoordenadas());
            snake.crecer(getCoordenadas());

        }else {
            snake.crecer(getCoordenadas());
        }
    }
}
