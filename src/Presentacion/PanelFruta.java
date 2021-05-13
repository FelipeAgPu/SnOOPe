package Presentacion;

import Aplicacion.*;

import javax.swing.*;
import java.awt.*;

public class PanelFruta extends JPanel {
    private Fruta[] frutas;
    int max, size, nFilas, nColumnas, res;
    SnOOPe snoope;
    PowerUp powerUp;

    /**
     * Constructor del panel de frutas
     * @param max Ancho máximo de la ventana
     * @param nFilas Cantidad de filas
     * @param nColumnas Cantidad de columnas
     * @param snoope Partida
     */
    public PanelFruta(int max, int nFilas, int nColumnas, SnOOPe snoope){
        this.max = max;
        this.nFilas = nFilas;
        this.nColumnas = nColumnas;
        this.size = max/nFilas;
        this.res = max % nFilas;
        this.snoope = snoope;

        this.frutas = snoope.getFrutas();
        this.powerUp = snoope.getPowerUp();
    }

    /**
     * Método que pinta el panel
     * @param pintor Pintor del panel
     */
    @Override
    public void paint(Graphics pintor){
        this.powerUp = snoope.getPowerUp();
        //Pintar frutas
        for (int i = 0; i < 2; i++) {
            pintor.setColor(frutas[i].getColor());
            pintor.fillRect(res/2+frutas[i].getCoordenadas()[0] * size, res/2+frutas[i].getCoordenadas()[1] * size, size-1,size-1);
            pintor.drawImage(new ImageIcon(frutas[i].getImg()).getImage(),res/2+frutas[i].getCoordenadas()[0] * size, res/2+frutas[i].getCoordenadas()[1] * size,size-1,size-1, null);
        }
        //Pintar PowerUp
        if (powerUp.getTipo() == null){
            pintor.setColor(new Color(56, 87, 53));
            pintor.fillRect(res/2+powerUp.getCoordenadas()[0] * size, res/2+powerUp.getCoordenadas()[1] * size, size-1,size-1);
        }else {
            pintor.drawImage(new ImageIcon(powerUp.getImg()).getImage(), res / 2 + powerUp.getCoordenadas()[0] * size, res / 2 + powerUp.getCoordenadas()[1] * size, size - 1, size - 1, null);
        }

        //Pintar Bloques Trampa
        for (Integer[] coor: snoope.getBloques()){
            pintor.drawImage(new ImageIcon("./images/bloqueNegro.png").getImage(), res / 2 + coor[0] * size, res / 2 + coor[1] * size, size - 1, size - 1, null);
        }

    }


}
