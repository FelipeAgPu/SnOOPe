package Presentacion;

import Aplicacion.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SnOOPeGUI extends JFrame {

    CardLayout cd;
    JPanel principal, vistaMenuPrincipal, vistaJugarSingle;
    ArrayList<PanelSnake> snakes = new ArrayList<>();
    JMenuBar menuBar;
    JMenuItem abrirMenu, guardarMenu, guardarComoMenu, salirMenu;
    JButton jugarBoton, abrirBoton, puntuacionBoton, salirBoton;
    JLabel fondo;
    String lastGame;
    GameModes gameMode;
    ImageIcon imgFondo, imgJugar, imgAbrir, imgSalir, imgPuntuacion;
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
        this.width = (int) ((2560/4)*3);
        this.height = (int) ((1440/4)*3);

        //Aguas
        this.x = 0;
        this.y = 0;

        //Diego
        //this.x = (int) ((2560/8));
        //this.y = (int) ((1440/8));

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
        this.imgAbrir = new ImageIcon("./images/abrir.png");
        this.imgPuntuacion = new ImageIcon("./images/puntuacion.png");
        this.imgSalir = new ImageIcon("./images/salir.png");
        jugarBoton = new JButton("");
        jugarBoton.setBounds((vistaMenuPrincipal.getWidth()/10)*4,(vistaMenuPrincipal.getHeight()/14)*5,300,100);
        jugarBoton.setIcon(imgJugar);
        jugarBoton.setContentAreaFilled(false);
        jugarBoton.setBorderPainted(false);
        vistaMenuPrincipal.add(jugarBoton);

        abrirBoton=new JButton(imgAbrir);
        abrirBoton.setBounds((vistaMenuPrincipal.getWidth()/10)*4,(vistaMenuPrincipal.getHeight()/14)*7,300,100);
        abrirBoton.setBorderPainted(false);
        abrirBoton.setContentAreaFilled(false);
        vistaMenuPrincipal.add(abrirBoton);

        puntuacionBoton = new JButton(imgPuntuacion);
        puntuacionBoton.setBounds((vistaMenuPrincipal.getWidth()/10)*3,(vistaMenuPrincipal.getHeight()/14)*9,700,100);
        puntuacionBoton.setBorderPainted(false);
        puntuacionBoton.setContentAreaFilled(false);
        vistaMenuPrincipal.add(puntuacionBoton);

        salirBoton = new JButton(imgSalir);
        salirBoton.setBounds((vistaMenuPrincipal.getWidth()/10)*4,(vistaMenuPrincipal.getHeight()/14)*11,300,100);
        salirBoton.setContentAreaFilled(false);
        salirBoton.setBorderPainted(false);
        vistaMenuPrincipal.add(salirBoton);


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

        abrirBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrir();
            }
        });

        salirBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
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
     * Método que reinicia el Juego pasando a la ventana de configuracion del modo en el que estaba el usuario
     */
    public void restart(){
        lastGame=gameMode.getAnterior();
        snoope = new SnOOPe(20,30);
        gameMode = new GameModes(this,this.snoope);
        switch (lastGame){
            case "SinglePlayer":
                gameMode.single();
                break;
            case "MultiPlayer":
                gameMode.multi();
                break;
            case "IA":
                gameMode.iA();
                break;
        }
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
                gameMode = new GameModes(this,snoope);
                try {
                    lastGame=gameMode.getAnterior();
                }catch (NullPointerException e){
                    lastGame=null;
                }
            }
            catch (SnOOPeException e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }

    /**
     * Método que guarda un archivo
     */
    public void guardar(){
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
        snakes.clear();
        snakes.add(new PanelSnake(principal.getHeight(), 20, 30, snoope.getSnakes().get(0),this));
        vistaJugarSingle.add(snakes.get(0), "Snake1");
        snakes.get(0).setLayout(null);
        snakes.get(0).setBounds(0, 0, vistaJugarSingle.getWidth(), vistaJugarSingle.getHeight());
        snakes.get(0).setOpaque(false);
        if(snoope.getSnakes().size()>1){
            snakes.add(new PanelSnake(principal.getHeight(), 20, 30, snoope.getSnakes().get(1),this));
            vistaJugarSingle.add(snakes.get(1), "Snake2");
            snakes.get(1).setLayout(null);
            snakes.get(1).setBounds(0, 0, vistaJugarSingle.getWidth(), vistaJugarSingle.getHeight());
            snakes.get(1).setOpaque(false);
        }


        PanelFruta fruta = new PanelFruta(principal.getHeight(), 20, 30, snoope);
        vistaJugarSingle.add(fruta, "Fruta");
        fruta.setLayout(null);
        fruta.setBounds(0, 0, vistaJugarSingle.getWidth(), vistaJugarSingle.getHeight());
        fruta.setOpaque(false);

        PanelStats stats = new PanelStats(snoope.getSnakes(), this);
        stats.setLayout(null);
        stats.setBounds(snakes.get(0).res/2+snakes.get(0).nColumnas*snakes.get(0).size+10, 0, principal.getWidth()-snakes.get(0).res/2+snakes.get(0).nColumnas*snakes.get(0).size, principal.getHeight());
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
                    snakes.get(0).getSnake().cambiarDireccion("UP");
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    snakes.get(0).getSnake().cambiarDireccion("DOWN");
                } else if (e.getKeyCode() == KeyEvent.VK_D) {
                    snakes.get(0).getSnake().cambiarDireccion("RIGHT");
                } else if (e.getKeyCode() == KeyEvent.VK_A) {
                    snakes.get(0).getSnake().cambiarDireccion("LEFT");
                } else if (e.getKeyCode() == KeyEvent.VK_E) {
                    if (snakes.get(0).getSnake().getPoder() instanceof Speed) {
                        snakes.get(0).getSnake().usaPowerUp();
                        Timer timer = new Timer();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                snakes.get(0).getSnake().setSpeed(false);
                            }
                        };
                        timer.schedule(task, 5000);

                    } else {
                        snakes.get(0).getSnake().usaPowerUp();
                    }
                }
                if (snakes.size() > 1) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        snakes.get(1).getSnake().cambiarDireccion("UP");
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        snakes.get(1).getSnake().cambiarDireccion("DOWN");
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        snakes.get(1).getSnake().cambiarDireccion("RIGHT");
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        snakes.get(1).getSnake().cambiarDireccion("LEFT");
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (snakes.get(1).getSnake().getPoder() instanceof Speed) {
                            snakes.get(1).getSnake().usaPowerUp();
                            Timer timer = new Timer();
                            TimerTask task = new TimerTask() {
                                @Override
                                public void run() {
                                    snakes.get(1).getSnake().setSpeed(false);
                                }
                            };
                            timer.schedule(task, 5000);

                        } else {
                            snakes.get(1).getSnake().usaPowerUp();
                        }
                    }
                }
            }
        };
        requestFocus(true);
        addKeyListener(listener);
    }

    /**
     * Método que pausa el juego
     */
    public void pausar(){
        snakes.get(0).isPaused = true;
        if (snakes.size()>1){
            snakes.get(1).isPaused = true;
        }
        gameMode.prepareElementosPausa();
        gameMode.prepareAccionesPausa();
        cd.show(principal, "Pausa");
    }

    public static void main(String[] args) {
        SnOOPeGUI gui = new SnOOPeGUI();
        gui.setVisible(true);
    }
}
