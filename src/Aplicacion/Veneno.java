package Aplicacion;

import javax.swing.*;

public class Veneno extends Fruta{
    public Veneno(SnOOPe partida) {
        super(partida);
        this.tipo = "Veneno";
        this.img = new ImageIcon("./images/veneno.png").getImage();
    }

    //Diagrama
    @Override
    public void esComida(Snake snake) throws SnOOPeException {
        throw new SnOOPeException(SnOOPeException.GAME_OVER);
    }
}
