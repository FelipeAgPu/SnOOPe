package Presentacion;

import javax.swing.*;
import java.awt.*;

public class PanelTablero extends JPanel {

    Color color = new Color(56, 87, 53);
    int max, size, nFilas, nColumnas, res;

    /**
     * Constructor del panel de tablero
     * @param max Ancho máximo de la ventana
     * @param nFilas Cantidad de filas
     * @param nColumnas Cantidad de columnas
     */
    public PanelTablero(int max, int nFilas, int nColumnas){
        this.max = max;
        this.nFilas = nFilas;
        this.nColumnas = nColumnas;
        this.size = max/nFilas;
        this.res = max % nFilas;
    }

    @Override
    /**
     * Método que pinta  el panel de tablero
     */
    public void paint(Graphics pintor){
        super.paint(pintor);
        pintor.setColor(color);
        for (int i = 0; i < nColumnas; i++) {
            for (int j = 0; j < nFilas; j++) {
                pintor.fillRect(res/2+i*size, res/2+j*size, size-1,size-1);
            }
        }
    }
}
