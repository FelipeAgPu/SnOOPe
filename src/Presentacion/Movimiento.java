package Presentacion;

import Aplicacion.SnOOPe;
import Aplicacion.SnOOPeException;
import Aplicacion.Snake;

import javax.swing.*;

public class Movimiento implements Runnable{
    PanelSnake snake;
    int velocidad, multiplicador, aumentador;
    boolean estado=true;
    SnOOPeGUI gui;

    /**
     * Creador de la clase encargada del movimiento de la serpiente
     * @param snake Serpiente a mover
     */
    public Movimiento(PanelSnake snake, SnOOPeGUI gui){
        this.gui = gui;
        this.snake = snake;
        this.velocidad= 200;
        this.aumentador = 1;
    }

    /**
     * Método que actualiza el estado de la serpiente
     */
    @Override
    public void run() {
        while(estado && !snake.isPaused) {
            if (!snake.getSnake().isCongelada()){
                try {
                    if(snake.getSnake().isLanzoFuego()) {
                        snake.getSnake().getFuego().avanzar(snake.getSnake());
                    }
                    snake.getSnake().avanzar();
                } catch (SnOOPeException e) {
                    for (PanelSnake panelSnake:gui.snakes) {
                        panelSnake.movimiento.estado=false;
                    }
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    gui.cd.show(gui.principal, "GameOver");
                }
                this.snake.repaint();
                try {
                    if (snake.getSnake().getSpeed()){
                        if(snake.getSnake().getNoSpeed()){
                            aumentador = 400;
                        }else{
                            aumentador = -100;
                        }
                    }else {
                        multiplicador = snake.getSnake().getSnake().size() / 5;
                        aumentador = 10 * multiplicador;
                    }

                    Thread.sleep(velocidad+aumentador);
                } catch (InterruptedException e) {
                    System.out.println("Error");
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
