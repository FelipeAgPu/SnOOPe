package Aplicacion;

import javax.swing.*;
import java.io.Serializable;

public class Estrella extends PowerUp implements Serializable {
    public Estrella(SnOOPe partida) {
        super(partida);
        this.tipo = "Fuego";
        this.img ="./images/fuego.png";
    }
}
