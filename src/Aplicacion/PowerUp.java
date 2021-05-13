package Aplicacion;

import java.awt.*;
import java.io.Serializable;

public class PowerUp extends Comestible implements Serializable {
    String img;

    public PowerUp(SnOOPe partida){
        super(partida);
    }

    public void esUsada(Snake snake){

    }

    public String getImg(){
        return img;
    }
}
