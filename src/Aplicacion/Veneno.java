package Aplicacion;

import javax.swing.*;
import java.io.Serializable;

public class Veneno extends Fruta implements Serializable {
    /**
     * Constructor fruta Arcoiris
     * @param partida Partida a la se va a agregar
     */
    public Veneno(SnOOPe partida) {
        super(partida);
        this.tipo = "Veneno";
        this.img = "./images/veneno.png";
    }

    /**
     * @See Aplicacion.Fruta.esComida
     */
    @Override
    public void esComida(Snake snake) throws SnOOPeException {
        throw new SnOOPeException(SnOOPeException.GAME_OVER);
    }
}
