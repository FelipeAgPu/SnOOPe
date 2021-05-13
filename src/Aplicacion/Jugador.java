package Aplicacion;

import java.awt.*;
import java.io.Serializable;

public class Jugador extends Snake implements Serializable {
    String nombre;

    /**
     * Constructor de la serpiente de un jugador
     * @param nombre Nombre de la serpiente
     * @param colorBody Color del cuerpo
     * @param colorHead Color de la cabeza
     * @param partida Partida a añadir
     */
    public Jugador(String nombre, Color colorBody, Color colorHead, SnOOPe partida){
        super(colorBody,colorHead, partida);
        this.nombre = nombre;
    }

    /**
     * @See Aplicacion.Snake.avanzar
     * @throws SnOOPeException Si la serpiente se estrella
     */
    @Override
    public void avanzar() throws SnOOPeException {
        igualarDireccion();
        Integer[] cabeza = snake.get(snake.size()-1);
        int moveX = 0;
        int moveY = 0;
        switch (direccion){
            case "UP":
                moveY=-1;
                break;
            case "DOWN":
                moveY=1;
                break;
            case "RIGHT":
                moveX=1;
                break;
            case "LEFT":
                moveX=-1;
                break;
        }

        int nuevoX = cabeza[0]+moveX;
        int nuevoY = cabeza[1]+moveY;

        //Tablero finito
        if (nuevoX>= partida.getnColumnas()){
            throw new SnOOPeException(SnOOPeException.GAME_OVER_WALL);
        }else if (nuevoX<0){
            throw new SnOOPeException(SnOOPeException.GAME_OVER_WALL);
        }
        if (nuevoY>= partida.getnFilas()){
            throw new SnOOPeException(SnOOPeException.GAME_OVER_WALL);
        }else if (nuevoY<0){
            throw new SnOOPeException(SnOOPeException.GAME_OVER_WALL);
        }

        Integer[] nuevaCabeza = {nuevoX, nuevoY};

        comer();
        recoge();

        this.snake.add(nuevaCabeza);
        this.snake.remove(0);
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}
