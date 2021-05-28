package Aplicacion;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Distraida extends Maquina implements Serializable {
    int movements=0;
    public Distraida(Color colorBody, Color colorHead, SnOOPe partida) {
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
    }

}
