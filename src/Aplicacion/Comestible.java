package Aplicacion;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public abstract class Comestible implements Serializable {
    protected Integer[] coordenadas;
    protected String tipo;
    protected SnOOPe partida;
    protected String img;

    /**
     * Constructor de un comestible
     * @param partida Partida en la que se va a añadir
     */
    public Comestible(SnOOPe partida){
        this.partida = partida;
        generarCoordenadas();
    }

    //Diagrama

    /**
     * Método que genera un par de coordenadas aleatorias que no esten ocupadas
     */
    public void generarCoordenadas(){
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
                    generarCoordenadas();
                    break;
                }
            }
            if (existe){
                break;
            }
        }
        if (!existe){
            this.coordenadas = coor;
        }
    }


    public Integer[] getCoordenadas() {
        return this.coordenadas;
    }

    public void setCoordenadas(Integer[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String coordenadasToString(){
        String ans = coordenadas[0] + " " + coordenadas[1];
        return ans;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
