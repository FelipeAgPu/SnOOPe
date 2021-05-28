package Aplicacion;

import Aplicacion.SnOOPe;
import Aplicacion.SnOOPeException;
import Aplicacion.Snake;

import javax.swing.*;
import java.io.Serializable;

public class Movimiento implements Runnable, Serializable {
    Snake snake;
    int velocidad, multiplicador, aumentador;
    boolean estado = true;

    /**
     * Creador de la clase encargada del movimiento de la serpiente
     *
     * @param snake Serpiente a mover
     */
    public Movimiento(Snake snake) {

        this.snake = snake;
        this.velocidad = 80;
        this.aumentador = 1;
    }

    /**
     * Método que actualiza el estado de la serpiente
     */
    @Override
    public void run() {
        while (estado) {
            try {
                snake.partida.printInfo();
                System.out.println("==============");
                snake.avanzar();
            } catch (SnOOPeException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.out.println(e.getMessage());
                System.exit(0);
            }
            try {
                multiplicador = snake.getSnake().size() / 5;
                aumentador = 10 * multiplicador;

                Thread.sleep(velocidad + aumentador);
            } catch (InterruptedException e) {

            }
        }
    }

    /**
     * Método que detiene el movimiento
     */
    public void stop() {
        this.estado = false;
    }
}
