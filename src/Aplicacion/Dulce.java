package Aplicacion;

import javax.swing.*;

public class Dulce extends Fruta{
    /**
     * Constructor fruta Arcoiris
     * @param partida Partida a la se va a agregar
     */
    public Dulce(SnOOPe partida) {
        super(partida);
        this.tipo = "Dulce";
        this.img = new ImageIcon("./images/dulce.png").getImage();
    }

    /**
     * @See Aplicacion.Fruta.esComida
     */
    @Override
    public void esComida(Snake snake) throws SnOOPeException {
        if (snake.getSnake().size()==1){
            throw new SnOOPeException(SnOOPeException.GAME_OVER_DULCES);
        }
        snake.snake.remove(0);
    }
}
