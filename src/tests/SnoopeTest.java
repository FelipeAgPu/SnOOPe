package tests;

import Aplicacion.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SnoopeTest {
    SnOOPe snnope;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        snnope = new SnOOPe(20,40);
        snnope.jugar("Nombre", new Color(0x861684), new Color(0x2C2CC6));
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void avanzarTest() {
        try {
            snnope.getSnakes().get(0).avanzar();
        }catch (SnOOPeException e){

        }
        assertEquals(11, snnope.getSnakes().get(0).getSnake().get(2)[0]);
        assertEquals(12, snnope.getSnakes().get(0).getSnake().get(2)[1]);

        try {
            snnope.getSnakes().get(0).avanzar();
        }catch (SnOOPeException e){

        }
        assertEquals(12, snnope.getSnakes().get(0).getSnake().get(2)[0]);
        assertEquals(12, snnope.getSnakes().get(0).getSnake().get(2)[1]);

    }

    @Test
    void cambiarDireccionTest() {
        snnope.getSnakes().get(0);
    }

    @Test
    void girarTest() {
    }

    @Test
    void noVolverTest() {
    }

    @Test
    void muereChoqueTest() {
    }

    @Test
    void suicidioTest() {

    }

    @Test
    void esComidaNormalTest() {
    }

    @Test
    void esComidaDulceTest() {
    }

    @Test
    void esComidaVenenoTest() {
    }

    @Test
    void esComidaArcoirisTest() {
    }


}