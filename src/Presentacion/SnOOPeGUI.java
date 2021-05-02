package Presentacion;

import Aplicacion.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class SnOOPeGUI extends JFrame {

    CardLayout cd;
    JPanel principal, vistaMenuPrincipal, vistaJugarSingle;
    PanelSnake snake;
    JMenuBar menuBar;
    JMenuItem abrirMenu, guardarMenu, guardarComoMenu, salirMenu;
    JButton jugarBoton;
    GameModes gameMode;
    int width,height, x, y;
    SnOOPe snoope;


    public SnOOPeGUI(){
        prepareElementos();
        prepareAcciones();
    }

    public void prepareElementos(){
        this.setTitle("SnOOPe");

        //Dimensiones de la pantalla para que quede centralizado y con dimensiones de 1/4 de la pantalla
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = (int) ((size.getWidth()/4)*3);
        this.height = (int) ((size.getHeight()/4)*3);

        this.x = (int) ((size.getWidth()/8));
        this.y = (int) ((size.getHeight()/8));

        this.setBounds(x, y, width, height);

        //Definimos el panel Principal para poder recorrer entre las diferentes vistas
        cd = new CardLayout();
        principal = new JPanel(cd);
        principal.setBounds(x, y, width, height);
        add(principal);

        prepareElementosMenuBarra();
        prepareElementosMenu();
        cd.show(principal,"MenuPpal");
    }

    public void prepareElementosMenu(){

        //vista del Menu Principal
        vistaMenuPrincipal = new JPanel();
        principal.add(vistaMenuPrincipal, "MenuPpal");
        vistaMenuPrincipal.setLayout(null);
        vistaMenuPrincipal.setBounds(x, y,width,height);
        vistaMenuPrincipal.setBackground(new Color(56, 87, 53));

        jugarBoton = new JButton("Jugar");
        jugarBoton.setBounds(vistaMenuPrincipal.getWidth()/4,vistaMenuPrincipal.getHeight()/4,vistaMenuPrincipal.getWidth()/4,vistaMenuPrincipal.getHeight()/4);
        vistaMenuPrincipal.add(jugarBoton);
    }

    public void prepareElementosMenuBarra(){
        //Menu de barra
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu archivo = new JMenu("Archivo");
        menuBar.add(archivo);

        //Items del menu
        abrirMenu = new JMenuItem("Abrir");
        archivo.add(abrirMenu);

        archivo.addSeparator();

        guardarMenu = new JMenuItem("Guardar");
        archivo.add(guardarMenu);

        guardarComoMenu = new JMenuItem("Guardar Como");
        archivo.add(guardarComoMenu);

        archivo.addSeparator();

        salirMenu = new JMenuItem("Salir");
        archivo.add(salirMenu);
    }

    public void prepareAcciones(){
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salir();
            }
        });
        prepareAccionesMenu();
        prepareAccionesMenuBarra();
    }

    public void prepareAccionesMenu(){
        jugarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jugar();
            }
        });
    }

    public void prepareAccionesMenuBarra(){
        //Acciones de las opciones de archivo
        abrirMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrir();
            }
        });
        guardarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });
        guardarComoMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });
        salirMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
    }

    private void jugar(){
        snoope = new SnOOPe(20,30);
        gameMode = new GameModes(this, this.snoope);
        cd.show(principal, "GameMode");
    }

    private void abrir(){
        JFileChooser fc = new JFileChooser();
        int sel = fc.showOpenDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            String name = file.getName();
            JOptionPane.showMessageDialog(null,"Funcionalidad Abrir en construccion.\n Archivo a abrir : "+ name);
        }
    }

    private void guardar(){
        JFileChooser fc = new JFileChooser();
        int sel =fc.showSaveDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            String name = file.getName();
            JOptionPane.showMessageDialog(null,"Funcionalidad Guardar en construccion.\n Archivo a guardar : "+name);
        }
    }

    private void salir(){
        int res = JOptionPane.showConfirmDialog(this,"Desea salir?", "salir", JOptionPane.YES_NO_OPTION);
        if (res==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    public void jugarSingle(){
        prepareElementosJuego();
        prepareAccionesJuego();
        gameMode.snake = this.snake;
    }

    private void prepareElementosJuego(){
        vistaJugarSingle = new JPanel();
        principal.add(vistaJugarSingle, "JuegoSingle");
        vistaJugarSingle.setLayout(null);
        vistaJugarSingle.setBounds(x, y,width, height);
        vistaJugarSingle.setBackground(new Color(246, 246, 246));
    }

    public void prepareAccionesJuego(){
        snake = new PanelSnake(principal.getHeight(), 20, 30, snoope.getSnakes().get(0));
        vistaJugarSingle.add(snake, "Snake");
        snake.setLayout(null);
        snake.setBounds(0, 0, vistaJugarSingle.getWidth(), vistaJugarSingle.getHeight());
        snake.setOpaque(false);

        PanelFruta fruta = new PanelFruta(principal.getHeight(), 20, 30, snoope);
        vistaJugarSingle.add(fruta, "Fruta");
        fruta.setLayout(null);
        fruta.setBounds(0, 0, vistaJugarSingle.getWidth(), vistaJugarSingle.getHeight());
        fruta.setOpaque(false);

        PanelStats stats = new PanelStats(snoope.getSnakes().get(0), this);
        stats.setLayout(null);
        stats.setBounds(snake.res/2+snake.nColumnas*snake.size, 0, principal.getWidth()-snake.res/2+snake.nColumnas*snake.size, principal.getHeight());
        vistaJugarSingle.add(stats);

        PanelTablero tablero = new PanelTablero(principal.getHeight(), 20, 30);
        vistaJugarSingle.add(tablero, "Tablero");
        tablero.setLayout(null);
        tablero.setBounds(0, 0, vistaJugarSingle.getWidth(), vistaJugarSingle.getHeight());


        keys();

        cd.show(principal, "JuegoSingle");
    }

    private void keys(){
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    snake.snake.cambiarDireccion("UP");
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    snake.snake.cambiarDireccion("DOWN");
                } else if (e.getKeyCode() == KeyEvent.VK_D) {
                    snake.snake.cambiarDireccion("RIGHT");
                } else if (e.getKeyCode() == KeyEvent.VK_A) {
                    snake.snake.cambiarDireccion("LEFT");
                }
            }
        };
        requestFocus(true);
        addKeyListener(listener);
    }

    public void jugarMulti(){

    }

    public static void main(String[] args) {
        SnOOPeGUI gui = new SnOOPeGUI();
        gui.setVisible(true);
    }
    /*
    - Comerse frutas
    - Reaparezcan frutas
    - Desaparecen frutas
     */

}
