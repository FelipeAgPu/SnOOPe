package Aplicacion;

import javax.swing.*;

public class Arcoiris extends Fruta{
    public Arcoiris(SnOOPe partida) {
        super(partida);
        this.tipo = "Arcoiris";
        this.img = new ImageIcon("./images/arcoiris.png").getImage();
    }

    @Override
    public void esComida(Snake snake){
        snake.snake.add(getCoordenadas());
        snake.snake.add(getCoordenadas());
        snake.snake.add(getCoordenadas());
    }
}
