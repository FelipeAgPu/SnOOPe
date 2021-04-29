package Aplicacion;

import java.awt.*;

public abstract class Comestible {
    protected Integer[] coordenadas;
    protected Color color;

    public Comestible(Integer[] coordenadas){
        this.coordenadas = coordenadas;
    }
}
