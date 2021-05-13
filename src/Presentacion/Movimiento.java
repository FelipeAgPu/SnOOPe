package Presentacion;

import Aplicacion.SnOOPe;
import Aplicacion.SnOOPeException;
import Aplicacion.Snake;

import javax.swing.*;

public class Movimiento implements Runnable{
    PanelSnake snake;
    int velocidad, multiplicador, aumentador;
    boolean estado=true;

    /**
     * Creador de la clase encargada del movimiento de la serpiente
     * @param snake Serpiente a mover
     */
    public Movimiento(PanelSnake snake){

        this.snake = snake;
        this.velocidad= 80;
        this.aumentador = 1;
    }

    /**
     * Método que actualiza el estado de la serpiente
     */
    @Override
    public void run() {
        while(estado && !snake.isPaused) {
            for (Snake snake: snake.getSnakes()){
                try {
                    snake.avanzar();
                } catch (SnOOPeException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    System.exit(0);
                }
                this.snake.repaint();
                try {
                    if (snake.getSpeed()){
                        aumentador = 0;
                    }else {
                        multiplicador = snake.getSnake().size() / 5;
                        aumentador = 10 * multiplicador;
                    }

                    Thread.sleep(velocidad+aumentador);
                } catch (InterruptedException e) {

                }
            }

        }
    }

    /**
     * Método que detiene el movimiento
     */
    public void stop(){
        this.estado = false;
    }
}
