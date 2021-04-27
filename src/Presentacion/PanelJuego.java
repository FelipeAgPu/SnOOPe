package Presentacion;

import javax.swing.*;
import java.awt.*;

public class PanelJuego extends JPanel {

    Color color = new Color(0x176117);
    int max, size, nCeldas;

    public PanelJuego(int max, int nCeldas){
        this.max = max;
        this.nCeldas = nCeldas;
        this.size = max/nCeldas;
    }

    @Override
    public void paint(Graphics pintor){
        super.paint(pintor);
        pintor.setColor(color);
        for (int i = 0; i < nCeldas; i++) {
            for (int j = 0; j < nCeldas; j++) {
                pintor.fillRect(i*size, j*size, size-1,size-1);
            }
            
        }
    }

}
