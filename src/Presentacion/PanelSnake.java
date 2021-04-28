package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PanelSnake extends JPanel {

    Color color = new Color(163, 33, 217);
    int max, size, nFilas, nColumnas, res;
    ArrayList<Integer[]> snake;
    String direccion="RIGHT";
    String nuevaDireccion="";

    Movimiento movimiento;
    Thread hilo;


    public PanelSnake(int max, int nFilas, int nColumnas){
        this.max = max;
        this.nFilas = nFilas;
        this.nColumnas = nColumnas;
        this.size = max/nFilas;
        this.res = max % nFilas;
        this.snake = new ArrayList<>();

        Integer[] a={2,2};
        Integer[] b={2,3};
        this.snake.add(a);
        this.snake.add(b);

        this.movimiento = new Movimiento(this);
        this.hilo = new Thread(movimiento);
        hilo.start();


    }

    @Override
    public void paint(Graphics pintor){
        super.paint(pintor);
        pintor.setColor(color);
        for (Integer[] coordenadas: this.snake){
            pintor.fillRect(res/2+coordenadas[0]*size, res/2+coordenadas[1]*size, size-1, size-1);
        }
    }

    public void avanzar(){
        //igualarDireccion();
        Integer[] cabeza = snake.get(snake.size()-1);
        int moveX = 0;
        int moveY = 0;
        switch (direccion){
            case "UP":
                moveY=-1;
                break;
            case "DOWN":
                moveY=1;
                break;
            case "RIGHT":
                moveX=1;
                break;
            case "LEFT":
                moveX=-1;
                break;
        }

        int nuevoX = cabeza[0]+moveX;
        int nuevoY = cabeza[1]+moveY;
        //Tablero finito
        if (nuevoX>= nColumnas){
            JOptionPane.showMessageDialog(null, "Muere");
            System.exit(0);
        }else if (nuevoX<0){
            JOptionPane.showMessageDialog(null, "Muere");
            System.exit(0);
        }
        if (nuevoY>= nFilas){
            JOptionPane.showMessageDialog(null, "Muere");
            System.exit(0);
        }else if (nuevoY<0){
            JOptionPane.showMessageDialog(null, "Muere");
            System.exit(0);
        }
        Integer[] nuevaCabeza = {nuevoX, nuevoY};

        this.snake.add(nuevaCabeza);
        this.snake.remove(0);
    }

    public void cambiarDireccion(String direccion){
        this.nuevaDireccion = direccion;
    }

    public void igualarDireccion(){
        this.direccion = this.nuevaDireccion;
    }

}
