package Aplicacion;

import javax.swing.*;
import java.awt.*;

public class Jugador extends Snake{
    String nombre;
    public Jugador(String nombre, Color colorBody, Color colorHead, SnOOPe partida){
        super(colorBody,colorHead, partida);
        this.nombre = nombre;
    }

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
            throw new SnOOPeException(SnOOPeException.GAME_OVER);
        }else if (nuevoX<0){
            throw new SnOOPeException(SnOOPeException.GAME_OVER);
        }
        if (nuevoY>= partida.getnFilas()){
            throw new SnOOPeException(SnOOPeException.GAME_OVER);
        }else if (nuevoY<0){
            throw new SnOOPeException(SnOOPeException.GAME_OVER);
        }

        Integer[] nuevaCabeza = {nuevoX, nuevoY};

        comer();

        this.snake.add(nuevaCabeza);
        this.snake.remove(0);
    }
}
