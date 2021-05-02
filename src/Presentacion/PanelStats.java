package Presentacion;

import Aplicacion.SnOOPe;
import Aplicacion.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelStats extends JPanel {
    int puntos;
    JLabel puntuacion;
    Snake snake;
    JButton pausa;
    SnOOPeGUI gui;

    public PanelStats(Snake snake, SnOOPeGUI gui){
        this.gui = gui;
        this.snake = snake;
        this.puntos = snake.getPuntos();

        prepareElementosStats();
        prepareAccionesStats();
    }

    private void prepareElementosStats(){
        puntuacion = new JLabel();
        puntuacion.setText("Puntuacion " + snake.getNombre() + ": " + puntos);
        puntuacion.setBounds(this.getX()+50, this.getY()+200, 200,75);
        add(puntuacion);
        setOpaque(false);

        pausa = new JButton();
        pausa.setText("Pausa");
        pausa.setBounds(this.getX()+50, this.getY()+300, 200,75);
        add(pausa);
    }

    private void prepareAccionesStats(){
        pausa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pausar();
            }
        });
    }

    private void pausar(){
        gui.snake.isPaused = true;

        gui.cd.show(gui.principal, "Pausa");
    }

    @Override
    public void paint(Graphics pintor) {
        super.paint(pintor);
        this.puntos = snake.getPuntos();
        puntuacion.setText("Puntuacion " + snake.getNombre() + ": " + puntos);
    }
}
