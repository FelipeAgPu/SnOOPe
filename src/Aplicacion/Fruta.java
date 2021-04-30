package Aplicacion;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Fruta extends Comestible{
    Image img;
    Color color;
    public Fruta(SnOOPe partida){
        super(partida);
        this.color = generateRandomColor();
    }

    public void esComida(Snake snake) throws SnOOPeException {

    }

    public Color generateRandomColor(){
        Random ran = new Random();
        int x = ran.nextInt(partida.getColores().size());
        return partida.getColores().get(x);
    }

    public Image getImg(){
        return img;
    }

    public Color getColor() {
        return color;
    }
}
