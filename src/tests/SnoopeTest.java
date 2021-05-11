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
        snnope.getSnakes().get(0).igualarDireccion();
        snnope.getSnakes().get(0).cambiarDireccion("UP");

        assertEquals("UP", snnope.getSnakes().get(0).getNuevaDireccion());
    }

    @Test
    void noVolverTest() {
        snnope.getSnakes().get(0).igualarDireccion();
        snnope.getSnakes().get(0).cambiarDireccion("DOWN");

        assertEquals("DOWN", snnope.getSnakes().get(0).getNuevaDireccion());

        //No debería cambiar
        snnope.getSnakes().get(0).igualarDireccion();
        snnope.getSnakes().get(0).cambiarDireccion("UP");

        assertEquals("DOWN", snnope.getSnakes().get(0).getNuevaDireccion());
    }

    @Test
    void muereChoqueTest() {
        Integer[] coordPrueba1 = {0,0};
        snnope.getFrutas()[0].setCoordenadas(coordPrueba1);

        Integer[] coordPrueba2 = {0,0};
        snnope.getFrutas()[1].setCoordenadas(coordPrueba2);


        for (int i = 0; i < 30; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){
                assertEquals(SnOOPeException.GAME_OVER_WALL, e.getMessage());
            }
        }
    }

    @Test
    void esComidaNormalTest() {
        snnope.getFrutas()[0] = new Normal(snnope);
        Integer[] coordPrueba1 = {11,12};
        snnope.getFrutas()[0].setCoordenadas(coordPrueba1);

        snnope.getFrutas()[1] = new Normal(snnope);
        Integer[] coordPrueba2 = {0,0};
        snnope.getFrutas()[1].setCoordenadas(coordPrueba2);

        for (int i = 0; i < 4; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){

            }
        }

        assertTrue(snnope.getSnakes().get(0).getSnake().size()>3);

    }

    @Test
    void esComidaDulceTest() {
        snnope.getFrutas()[0] = new Dulce(snnope);
        Integer[] coordPrueba1 = {11,12};
        snnope.getFrutas()[0].setCoordenadas(coordPrueba1);

        Integer[] coordPrueba2 = {0,0};
        snnope.getFrutas()[1].setCoordenadas(coordPrueba2);

        for (int i = 0; i < 4; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){

            }
        }

        assertTrue(snnope.getSnakes().get(0).getSnake().size()<3);
    }

    @Test
    void esComidaVenenoTest() {
        snnope.getFrutas()[0] = new Veneno(snnope);
        Integer[] coordPrueba1 = {11,12};
        snnope.getFrutas()[0].setCoordenadas(coordPrueba1);

        Integer[] coordPrueba2 = {0,0};
        snnope.getFrutas()[1].setCoordenadas(coordPrueba2);

        for (int i = 0; i < 4; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){
                assertEquals(SnOOPeException.GAME_OVER, e.getMessage());
            }
        }
    }

    @Test
    void esComidaArcoirisTest() {
        snnope.getFrutas()[0] = new Arcoiris(snnope);
        Integer[] coordPrueba1 = {11,12};
        snnope.getFrutas()[0].setCoordenadas(coordPrueba1);

        Integer[] coordPrueba2 = {0,0};
        snnope.getFrutas()[1].setCoordenadas(coordPrueba2);

        for (int i = 0; i < 6; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){

            }
        }

        assertTrue(snnope.getSnakes().get(0).getSnake().size()==6);
    }


}