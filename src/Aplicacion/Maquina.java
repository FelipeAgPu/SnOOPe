package Aplicacion;

import java.awt.*;
import java.io.Serializable;

public class Maquina extends Snake implements Serializable {
    public Maquina(Color colorBody, Color colorHead, SnOOPe partida) {
        super("Carlito", colorBody, colorHead, partida);
    }

    /**
     * Método que hace que la serpiente avance
     * @throws SnOOPeException Si la serpiente se estrella
     */
    @Override
    public void avanzar() throws SnOOPeException {
        decidirDireccion();
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

    public void decidirDireccion(){}

    protected void chocaPared(){
        Integer[] cabeza = this.getSnake().get(snake.size()-1);
        if (cabeza[0]==0 && getNuevaDireccion().equals("LEFT")){
            decidirDireccion();
        }else if (cabeza[0]==29 && getNuevaDireccion().equals("RIGHT")){
            decidirDireccion();
        }else if (cabeza[1]==0 && getNuevaDireccion().equals("UP")){
            decidirDireccion();
        }else if (cabeza[1]==19 && getNuevaDireccion().equals("DOWN")){
            decidirDireccion();
        }
    }
}
