package Aplicacion;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Prudente extends Maquina implements Serializable {
    public Prudente(Color colorBody, Color colorHead, SnOOPe partida) {
        super(colorBody, colorHead, partida);
    }

    @Override
    public void decidirDireccion(){
        Random rn = new Random();
        int x = rn.nextInt(4);
        switch (x){
            case 0:
                this.cambiarDireccion("UP");
                break;
            case 1:
                this.cambiarDireccion("RIGHT");
                break;
            case 2:
                this.cambiarDireccion("DOWN");
                break;
            case 3:
                this.cambiarDireccion("LEFT");
                break;
        }
        chocaPared();
        recogePowerUp();
    }

    private void recogePowerUp() {
        Integer[] cabeza = this.getSnake().get(snake.size()-1);
        Integer newX=cabeza[0], newY=cabeza[1];
        switch (nuevaDireccion){
            case "UP":
                newY--;
                if (newY.equals(partida.getPowerUp().getCoordenadas()[1]) && newX.equals(partida.getPowerUp().getCoordenadas()[0])){
                    decidirDireccion();
                }
                break;
            case "DOWN":
                newY++;
                if(newY.equals(partida.getPowerUp().getCoordenadas()[1]) && newX.equals(partida.getPowerUp().getCoordenadas()[0])){
                    decidirDireccion();
                }
                break;
            case "RIGHT":
                newX++;
                if(newY.equals(partida.getPowerUp().getCoordenadas()[1]) && newX.equals(partida.getPowerUp().getCoordenadas()[0])){
                    decidirDireccion();
                }
                break;
            case "LEFT":
                newX--;
                if(newY.equals(partida.getPowerUp().getCoordenadas()[1]) && newX.equals(partida.getPowerUp().getCoordenadas()[0])){
                    decidirDireccion();
                }
                break;
        }
    }
}
