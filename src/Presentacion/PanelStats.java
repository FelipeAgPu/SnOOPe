package Presentacion;

import Aplicacion.SnOOPe;
import Aplicacion.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelStats extends JPanel {
    int puntos;
    String poder;
    JLabel[] puntuaciones, powerUps;
    ArrayList<Snake> snakes;
    JButton pausa;
    SnOOPeGUI gui;

    /**
     * Contructo del panel de estadísticas
     * @param snakes Serpiente que esta jugando
     * @param gui GUI del programa
     */
    public PanelStats(ArrayList<Snake> snakes, SnOOPeGUI gui){
        this.gui = gui;
        this.snakes = snakes;
        this.puntuaciones = new JLabel[2];
        this.powerUps = new  JLabel[2];

        for (int i = 0; i < snakes.size(); i++) {
            this.puntos = snakes.get(i).getPuntos();
            try {
                this.poder = snakes.get(i).getPoder().getTipo();
            }catch (NullPointerException e){
                this.poder = "Sin poder";
            }
        }

        prepareElementosStats();
        prepareAccionesStats();
    }

    /**
     * Método que prepara los elementos visuales del panel
     */
    private void prepareElementosStats(){
        int index = 0;
        for (Snake snake: snakes){
            puntuaciones[index] = new JLabel();
            puntuaciones[index].setText("Puntuacion " + snake.getNombre() + ": " + puntos);
            puntuaciones[index].setBounds(this.getX()+50, this.getY()+200 * (index+1), 200,50);
            add(puntuaciones[index]);

            powerUps[index] = new JLabel();
            powerUps[index].setText("Power Up " + snake.getNombre() + ": " + poder);
            powerUps[index].setBounds(this.getX()+50, this.getY()+225 * (index+1), 200,50);
            add(powerUps[index]);
            setOpaque(false);

            index++;
        }

        pausa = new JButton();
        pausa.setText("Pausa");
        pausa.setBounds(this.getX()+50, this.getY()+600, 200,75);
        add(pausa);
    }

    /**
     * Método que prepara las acciones del panel
     */
    private void prepareAccionesStats(){
        pausa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pausar();
                gui.abrirMenu.setVisible(false);
            }
        });
    }

    /**
     * Método que pausa el juego
     */
    private void pausar(){
        gui.snake.isPaused = true;

        gui.cd.show(gui.principal, "Pausa");
    }

    /**
     * Método que pinta el panel
     * @param pintor Pintor del panel
     */
    @Override
    public void paint(Graphics pintor) {
        super.paint(pintor);

        for (int i = 0; i < snakes.size(); i++) {
            this.puntos = snakes.get(i).getPuntos();
            try {
                this.poder = snakes.get(i).getPoder().getTipo();
            }catch (NullPointerException e){
                this.poder = "Sin poder";
            }
            puntuaciones[i].setText("Puntuacion " + snakes.get(i).getNombre() + ": " + puntos);
            powerUps[i].setText("Power Up " + snakes.get(i).getNombre() + ": " + poder);
        }


    }
}
