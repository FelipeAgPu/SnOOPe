package Aplicacion;

import javax.swing.*;
import java.io.Serializable;
import java.util.Random;

public class Bloque extends PowerUp implements Serializable {
    public Bloque(SnOOPe partida) {
        super(partida);
        this.tipo = "Bloque";
        this.img = "./images/bloque.jpg";

    }

    @Override
    public void esUsada(Snake snake){
        partida.addBloque(generarTrampa());
    }

    /**
     * Método que genera un par de coordenadas aleatorias que no esten ocupadas
     */
    public Integer[] generarTrampa(){
        boolean existe = false;

        //Creamos coordenadas aleatorias
        Random ran = new Random();
        int x = ran.nextInt(partida.getnColumnas());
        int y = ran.nextInt(partida.getnFilas());
        Integer[] coor = {x,y};

        //Revisamos que no este sobre las serpientes
        for (Snake snake: partida.getSnakes()){
            for (Integer[] coord: snake.getSnake()){
                if (coord == coor){
                    existe = true;
                    generarTrampa();
                    break;
                }
            }
            if (existe){
                break;
            }
        }
        if (!existe){
            return coor;
        }
        return coor;
    }
}
