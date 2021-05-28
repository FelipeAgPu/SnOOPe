package Aplicacion;

import javax.swing.*;
import java.io.Serializable;

public class Dulce extends Fruta implements Serializable {
    /**
     * Constructor fruta Arcoiris
     * @param partida Partida a la se va a agregar
     */
    public Dulce(SnOOPe partida) {
        super(partida);
        this.tipo = "Dulce";
        this.img = "./images/dulce.png";
    }

    /**
     * @See Aplicacion.Fruta.esComida
     */
    @Override
    public void esComida(Snake snake) throws SnOOPeException {
        if (partida.getSnakes().size()>1){
            for (int i=0; i<2; i++){
                if (partida.getSnakes().get(i) != snake){
                    if (partida.getSnakes().get(i).getColorHead() == this.color || partida.getSnakes().get(i).getColorBody() == this.color){
                        snake.decrecer();
                        snake.decrecer();
                    }else {
                        snake.decrecer();
                    }
                }
            }
        }else{
            if (snake.getSnake().size()==1){
                throw new SnOOPeException(SnOOPeException.GAME_OVER_DULCES);
            }
            if (snake.getColorHead() == this.color || snake.getColorBody() == this.color){
                snake.decrecer();
                snake.decrecer();
            }else {
                snake.decrecer();
            }

        }

    }
}
