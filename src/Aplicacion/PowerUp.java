package Aplicacion;

import java.awt.*;
import java.io.Serializable;

public class PowerUp extends Comestible implements Serializable {
    String img;

    public PowerUp(SnOOPe partida){
        super(partida);
    }

    /**
     * Metodo que usa el powerUp obtenido de la serpiente, si esta no tiene powerUp no hace nada
     * @param snake serpiente con el powerUp
     */
    public void esUsada(Snake snake){

    }

    public String getImg(){
        return img;
    }
}
