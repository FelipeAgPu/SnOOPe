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
    Font fuente;
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
        this.fuente = new Font("OCR A Extended",Font.PLAIN,30);

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
            puntuaciones[index].setText("<html><div style='text-align: center;'> Puntuación"+snake.getNombre()+"<br>"+puntos +"</div></html>");
            puntuaciones[index].setBounds(this.getX()+50, this.getY()+200 + (index)*250, 300,100);
            puntuaciones[index].setFont(fuente);
            add(puntuaciones[index]);

            powerUps[index] = new JLabel();
            powerUps[index].setText("<html><div style='text-align: center;'> Poder "+snake.getNombre()+"<br>"+poder +"</div></html>");
            powerUps[index].setBounds(this.getX()+80, this.getY()+300 + (index)*250, 300,100);
            powerUps[index].setFont(fuente);
            add(powerUps[index]);
            setOpaque(false);

            index++;
        }

        pausa = new JButton(new ImageIcon("./images/Pausa.png"));
        pausa.setBounds(this.getX()+90, this.getY()+850, 200,75);
        pausa.setBorderPainted(false);
        pausa.setContentAreaFilled(false);
        add(pausa);

        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0,gui.width,gui.height);
        fondo.setIcon(new ImageIcon("./images/fondo1.png"));

        this.add(fondo);
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
        gui.snakes.get(0).isPaused = true;
        if (gui.snakes.size()>1){
            gui.snakes.get(1).isPaused = true;
        }

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
            puntuaciones[i].setText("<html><div style='text-align: center;'> Puntuación "+snakes.get(i).getNombre()+"<br>"+puntos +"</div></html>");
            powerUps[i].setText("<html><div style='text-align: center;'> Poder "+snakes.get(i).getNombre()+"<br>"+poder +"</div></html>");
        }


    }
}
