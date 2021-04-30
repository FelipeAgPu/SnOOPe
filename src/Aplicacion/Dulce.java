package Aplicacion;

import javax.swing.*;

public class Dulce extends Fruta{
    public Dulce(SnOOPe partida) {
        super(partida);
        this.tipo = "Dulce";
        this.img = new ImageIcon("./images/dulce.png").getImage();
    }

    @Override
    public void esComida(Snake snake){
        if (snake.getColorHead() == this.color || snake.getColorBody() == this.color){
            snake.snake.remove(0);
            snake.snake.remove(0);
        }else {
            snake.snake.remove(0);
        }
    }
}
