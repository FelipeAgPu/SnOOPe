package Aplicacion;

import javax.swing.*;

public class Arcoiris extends Fruta{
    /**
     * Constructor fruta Arcoiris
     * @param partida Partida a la se va a agregar
     */
    public Arcoiris(SnOOPe partida) {
        super(partida);
        this.tipo = "Arcoiris";
        this.img = new ImageIcon("./images/arcoiris.png").getImage();
    }

    /**
     * @See Aplicacion.Fruta.esComida
     */
    @Override
    public void esComida(Snake snake){
        snake.snake.add(getCoordenadas());
        snake.snake.add(getCoordenadas());
        snake.snake.add(getCoordenadas());
    }
}
