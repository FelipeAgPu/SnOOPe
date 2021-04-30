package Presentacion;

import Aplicacion.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PanelFruta extends JPanel {
    private Fruta[] frutas;
    int max, size, nFilas, nColumnas, res;
    SnOOPe snoope;

    public PanelFruta(int max, int nFilas, int nColumnas, SnOOPe snoope){
        this.max = max;
        this.nFilas = nFilas;
        this.nColumnas = nColumnas;
        this.size = max/nFilas;
        this.res = max % nFilas;
        this.snoope = snoope;

        this.frutas = snoope.getFrutas();
    }

    @Override
    public void paint(Graphics pintor){
        for (int i = 0; i < 2; i++) {
            pintor.setColor(frutas[i].getColor());
            pintor.fillRect(res/2+frutas[i].getCoordenadas()[0] * size, res/2+frutas[i].getCoordenadas()[1] * size, size-1,size-1);
            pintor.drawImage(frutas[i].getImg(),res/2+frutas[i].getCoordenadas()[0] * size, res/2+frutas[i].getCoordenadas()[1] * size,size-1,size-1, null);
        }
    }


}
