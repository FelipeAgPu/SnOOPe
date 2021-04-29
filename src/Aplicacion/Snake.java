package Aplicacion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Snake{
    protected ArrayList<Integer[]> snake;
    protected Color colorBody, colorHead;
    protected String direccion="UP";
    protected String nuevaDireccion="";
    protected SnOOPe partida;

    public Snake(Color colorBody, Color colorHead, SnOOPe partida){
        this.colorBody = colorBody;
        this.colorHead = colorHead;
        this.partida = partida;

        this.snake = new ArrayList<>();

        Integer[] a={10,10};
        Integer[] b={10,11};
        Integer[] c={10,12};
        this.snake.add(a);
        this.snake.add(b);
        this.snake.add(c);
    }

    public void avanzar() throws SnOOPeException {

    }

    public void cambiarDireccion(String direccion){
        if (direccion.equals("UP") && !this.direccion.equals("DOWN")){
            this.nuevaDireccion = direccion;
        }else if (direccion.equals("DOWN") && !this.direccion.equals("UP")){
            this.nuevaDireccion = direccion;
        }else if (direccion.equals("RIGHT") && !this.direccion.equals("LEFT")){
            this.nuevaDireccion = direccion;
        }else if(direccion.equals("LEFT") && !this.direccion.equals("RIGHT")){
            this.nuevaDireccion = direccion;
        }
    }

    public void igualarDireccion(){
        this.direccion = this.nuevaDireccion;
    }

    public ArrayList<Integer[]> getSnake() {
        return snake;
    }

    public Color getColorBody() {
        return colorBody;
    }

    public void setColorBody(Color colorBody) {
        this.colorBody = colorBody;
    }

    public Color getColorHead() {
        return colorHead;
    }

    public void setColorHead(Color colorHead) {
        this.colorHead = colorHead;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNuevaDireccion() {
        return nuevaDireccion;
    }

    public void setNuevaDireccion(String nuevaDireccion) {
        this.nuevaDireccion = nuevaDireccion;
    }

    public SnOOPe getPartida() {
        return partida;
    }

    public void setPartida(SnOOPe partida) {
        this.partida = partida;
    }
}
