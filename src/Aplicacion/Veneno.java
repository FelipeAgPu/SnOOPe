package Aplicacion;

import javax.swing.*;

public class Veneno extends Fruta{
    /**
     * Constructor fruta Arcoiris
     * @param partida Partida a la se va a agregar
     */
    public Veneno(SnOOPe partida) {
        super(partida);
        this.tipo = "Veneno";
        this.img = new ImageIcon("./images/veneno.png").getImage();
    }

    /**
     * @See Aplicacion.Fruta.esComida
     */
    @Override
    public void esComida(Snake snake) throws SnOOPeException {
        throw new SnOOPeException(SnOOPeException.GAME_OVER);
    }
}
