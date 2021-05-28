package Aplicacion;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Fruta extends Comestible implements Serializable {
    Color color;

    /**
     * Construuctor de una fruta
     * @param partida Partida a la que se va a agregar
     */
    public Fruta(SnOOPe partida){
        super(partida);
        this.color = generateRandomColor();
    }

    /**
     * Método que cambia a la serpiente según lo que haya comido
     * @param snake Serpiente que se come la fruta
     * @throws SnOOPeException Si se come un veneno
     */
    public void esComida(Snake snake) throws SnOOPeException {

    }

    /**
     * Método que genera un color aleatorio
     * @return Color generado
     */
    public Color generateRandomColor(){
        Random ran = new Random();
        int x = ran.nextInt(partida.getColores().size());
        return partida.getColores().get(x);
    }

    public String getImg(){
        return img;
    }

    public Color getColor() {
        return color;
    }
}
