package Presentacion;

import Aplicacion.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class SnOOPeGUI extends JFrame {

    CardLayout cd;
    JPanel principal, vistaMenuPrincipal, vistaJugarSingle;
    PanelSnake snake;
    JMenuBar menuBar;
    JMenuItem abrirMenu, guardarMenu, guardarComoMenu, salirMenu;
    JButton jugarBoton;
    JLabel fondo;
    GameModes gameMode;
    ImageIcon imgFondo, imgJugar;
    int width,height, x, y;
    SnOOPe snoope;

    /**
     * Constructor del GUI
     */
    public SnOOPeGUI(){
        prepareElementos();
        prepareAcciones();
    }

    /**
     * Método que prepara todos los elementos visuales del Menú Principal
     */
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

    /**
     * Método que prepara todos los elementos visuales del Menú Inicial
     */
    public void prepareElementosMenu(){

        //vista del Menu Principal
        vistaMenuPrincipal = new JPanel();
        principal.add(vistaMenuPrincipal, "MenuPpal");
        vistaMenuPrincipal.setLayout(null);
        vistaMenuPrincipal.setBounds(x, y,width,height);

        this.imgJugar = new ImageIcon("./images/jugar.png");
        jugarBoton = new JButton("");
        jugarBoton.setBounds(vistaMenuPrincipal.getWidth()/4,vistaMenuPrincipal.getHeight()/4,vistaMenuPrincipal.getWidth()/2,vistaMenuPrincipal.getHeight()/2);
        jugarBoton.setIcon(imgJugar);
        jugarBoton.setContentAreaFilled(false);
        jugarBoton.setBorderPainted(false);
        vistaMenuPrincipal.add(jugarBoton);


        ImageIcon imgTitulo = new ImageIcon("./images/titulo.png");
        this.imgFondo = new ImageIcon("./images/fondo1.png");

        JLabel titulo = new JLabel();
        titulo.setBounds(vistaMenuPrincipal.getWidth()/4, 0,width,300);
        titulo.setIcon(imgTitulo);
        vistaMenuPrincipal.add(titulo);

        this.fondo = new JLabel();
        fondo.setBounds(0, 0,width,height);
        fondo.setIcon(imgFondo);
        vistaMenuPrincipal.add(fondo);



    }

    /**
     * Método que prepara todos los elementos visuales del Menú de Barra
     */
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

    /**
     * Método que prepara todas las acciones del Menú Principal
     */
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

    /**
     * Método que prepara todas las acciones del Menú Inicial
     */
    public void prepareAccionesMenu(){
        jugarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jugar();
            }
        });
    }

    /**
     * Método que prepara todas las acciones del Menú de Barra
     */
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

    /**
     * Método que inicia el SnOOPe y pasa a la ventan de selección de modo
     */
    private void jugar(){
        snoope = new SnOOPe(20,30);
        gameMode = new GameModes(this, this.snoope);
        cd.show(principal, "GameMode");
    }

    /**
     * Método que abre un archivo
     */
    private void abrir(){
        JFileChooser fc = new JFileChooser();
        int sel = fc.showOpenDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            try {
                snoope= snoope.abrir(file);
                snoope.setTimers();
                prepareElementosJuego();
                prepareAccionesJuego();
            }
            catch (SnOOPeException e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            }
    }

    /**
     * Método que guarda un archivo
     */
    private void guardar(){
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Guardar");
        fc.setApproveButtonText("Guardar");
        int sel = fc.showOpenDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            try {
                snoope.guardar(file);
            }catch (SnOOPeException e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }

    /**
     * Método que cierra el programa
     */
    private void salir(){
        int res = JOptionPane.showConfirmDialog(this,"Desea salir?", "salir", JOptionPane.YES_NO_OPTION);
        if (res==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    /**
     * Método que inicia una partida individual
     */
    public void jugarPartida(){
        prepareElementosJuego();
        prepareAccionesJuego();
        gameMode.snake = this.snake;
    }

    /**
     * Método que inicia el panel de la pantalla de partida individual
     */
    private void prepareElementosJuego(){
        vistaJugarSingle = new JPanel();
        principal.add(vistaJugarSingle, "JuegoSingle");
        vistaJugarSingle.setLayout(null);
        vistaJugarSingle.setBounds(x, y,width, height);
        vistaJugarSingle.setBackground(new Color(246, 246, 246));
    }

    /**
     * Método que inicia los paneles que se actualizan y añade los keylisteners
     */
    public void prepareAccionesJuego(){
        snake = new PanelSnake(principal.getHeight(), 20, 30, snoope.getSnakes(),this);
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

    /**
     * Método que crea y añade keylisteners al frame
     */
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
                    snake.getSnakes().get(0).cambiarDireccion("UP");
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    snake.getSnakes().get(0).cambiarDireccion("DOWN");
                } else if (e.getKeyCode() == KeyEvent.VK_D) {
                    snake.getSnakes().get(0).cambiarDireccion("RIGHT");
                } else if (e.getKeyCode() == KeyEvent.VK_A) {
                    snake.getSnakes().get(0).cambiarDireccion("LEFT");
                } else if (e.getKeyCode() == KeyEvent.VK_E){
                    if(snake.getSnakes().get(0).getPoder()instanceof Speed){
                        snake.getSnakes().get(0).usaPowerUp();
                        Timer timer = new Timer();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                snake.getSnakes().get(0).setSpeed(false);
                            }
                        };
                        timer.schedule(task, 5000);
                        
                    }
                    else{
                        snake.getSnakes().get(0).usaPowerUp();
                    }
                }else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    snake.getSnakes().get(1).cambiarDireccion("UP");
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    snake.getSnakes().get(1).cambiarDireccion("DOWN");
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    snake.getSnakes().get(1).cambiarDireccion("RIGHT");
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    snake.getSnakes().get(1).cambiarDireccion("LEFT");
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (snake.getSnakes().get(1).getPoder() instanceof Speed) {
                        snake.getSnakes().get(0).usaPowerUp();
                        Timer timer = new Timer();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                snake.getSnakes().get(0).setSpeed(false);
                            }
                        };
                        timer.schedule(task, 5000);

                    } else {
                        snake.getSnakes().get(1).usaPowerUp();
                    }
                }
            }
        };
        requestFocus(true);
        addKeyListener(listener);
    }

    public static void main(String[] args) {
        SnOOPeGUI gui = new SnOOPeGUI();
        gui.setVisible(true);
    }
}
