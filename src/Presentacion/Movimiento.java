package Presentacion;

public class Movimiento implements Runnable{
    PanelSnake snake;
    boolean estado=true;

    public Movimiento(PanelSnake snake){
        this.snake = snake;
    }

    @Override
    public void run() {
        while(estado) {
            snake.avanzar();
            snake.repaint();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
        }
    }

    public void stop(){
        this.estado = false;
    }
}
