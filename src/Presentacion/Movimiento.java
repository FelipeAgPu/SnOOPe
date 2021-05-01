package Presentacion;

import Aplicacion.SnOOPeException;

import javax.swing.*;

public class Movimiento implements Runnable{
    PanelSnake snake;
    boolean estado=true;

    public Movimiento(PanelSnake snake){
        this.snake = snake;
    }

    @Override
    public void run() {
        while(estado) {
            try {
                snake.snake.avanzar();
            } catch (SnOOPeException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.exit(0);
            }
            snake.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }
        }
    }

    public void stop(){
        this.estado = false;
    }
}
