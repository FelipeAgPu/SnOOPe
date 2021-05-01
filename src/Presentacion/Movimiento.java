package Presentacion;

import Aplicacion.SnOOPe;
import Aplicacion.SnOOPeException;
import Aplicacion.Snake;

import javax.swing.*;

public class Movimiento implements Runnable{
    PanelSnake snake;
    int velocidad, multiplicador;
    boolean estado=true;

    public Movimiento(PanelSnake snake){

        this.snake = snake;
        this.velocidad= 80;
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
                if (snake.getSnake().getSnake().size()%5==0){
                    multiplicador=snake.getSnake().getSnake().size()/5;
                    int aumentador = 50*multiplicador;
                }

                Thread.sleep(velocidad+multiplicador);
            } catch (InterruptedException e) {

            }
        }
    }

    public void stop(){
        this.estado = false;
    }
}
