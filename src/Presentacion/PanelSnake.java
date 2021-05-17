package Presentacion;

import Aplicacion.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PanelSnake extends JPanel {

    int max, size, nFilas, nColumnas, res;
    Snake snake;
    boolean isPaused = false;
    SnOOPeGUI gui;

    Movimiento movimiento;
    Thread hilo;

    /**
     * Creador del panel Snake
     * @param snakes Serpientes a dibujar
     * @param max Ancho máximo de la ventana
     * @param nFilas Cantidad de filas
     * @param nColumnas Cantidad de columnas
     */
    public PanelSnake(int max, int nFilas, int nColumnas, Snake snake, SnOOPeGUI gui){
        this.gui = gui;
        this.max = max;
        this.nFilas = nFilas;
        this.nColumnas = nColumnas;
        this.size = max/nFilas;
        this.res = max % nFilas;

        this.snake = snake;

        this.movimiento = new Movimiento(this, gui);
        this.hilo = new Thread(movimiento);
        hilo.start();

    }

    /**
     * Método que pinta el panel
     * @param pintor Pintor del panel
     */
    @Override
    public void paint(Graphics pintor){
        super.paint(pintor);
        pintor.setColor(snake.getColorBody());
        for (Integer[] coordenadas: snake.getSnake()){
            if (coordenadas == snake.getSnake().get(snake.getSnake().size()-1)){
                pintor.setColor(snake.getColorHead());
                pintor.fillRect(res/2+coordenadas[0]*size, res/2+coordenadas[1]*size, size-1, size-1);
            }else {
                pintor.fillRect(res / 2 + coordenadas[0] * size, res / 2 + coordenadas[1] * size, size - 1, size - 1);
            }
        }

    }

    public Snake getSnake() {
        return snake;
    }
}
