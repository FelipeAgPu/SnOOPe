package tests;

import Aplicacion.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SnoopeTest {
    SnOOPe snnope;
    ArrayList<String> frutas = new ArrayList<>();
    ArrayList<String> powerUps = new ArrayList<>();
    File archivoDat = new File("./tests/test.dat");

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        frutas.add("Normal");
        frutas.add("Dulce");
        frutas.add("Arcoiris");
        frutas.add("Veneno");

        powerUps.add("Speed");
        powerUps.add("Fuego");
        powerUps.add("Lupa");
        powerUps.add("Division");
        powerUps.add("Bloque");

        snnope = new SnOOPe(20,40);
        snnope.jugar("Nombre", new Color(0x861684), new Color(0x2C2CC6), frutas, powerUps);


    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    //Tests Entrega 1

    @Test
    void avanzarTest() {
        try {
            snnope.getSnakes().get(0).avanzar();
        }catch (SnOOPeException e){

        }
        assertEquals(3, snnope.getSnakes().get(0).getSnake().get(2)[0]);
        assertEquals(0, snnope.getSnakes().get(0).getSnake().get(2)[1]);

        try {
            snnope.getSnakes().get(0).avanzar();
        }catch (SnOOPeException e){

        }
        assertEquals(4, snnope.getSnakes().get(0).getSnake().get(2)[0]);
        assertEquals(0, snnope.getSnakes().get(0).getSnake().get(2)[1]);
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
        Integer[] coordPrueba1 = {4,0};
        snnope.getFrutas()[0].setCoordenadas(coordPrueba1);

        snnope.getFrutas()[1] = new Normal(snnope);
        Integer[] coordPrueba2 = {15,15};
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
        Integer[] coordPrueba1 = {4,0};
        snnope.getFrutas()[0].setCoordenadas(coordPrueba1);

        Integer[] coordPrueba2 = {15,15};
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
        Integer[] coordPrueba1 = {4,0};
        snnope.getFrutas()[0].setCoordenadas(coordPrueba1);

        Integer[] coordPrueba2 = {15,15};
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
        Integer[] coordPrueba1 = {4,0};
        snnope.getFrutas()[0].setCoordenadas(coordPrueba1);

        Integer[] coordPrueba2 = {15,15};
        snnope.getFrutas()[1].setCoordenadas(coordPrueba2);

        for (int i = 0; i < 6; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){

            }
        }

        assertTrue(snnope.getSnakes().get(0).getSnake().size()==6);
    }

    //Tests Entrega 2

    @Test
    void esRecogidaSpeedFastTest() {
        snnope.setPowerUp(new Speed(snnope));
        Integer[] coordPrueba = {4,0};
        snnope.getPowerUp().setCoordenadas(coordPrueba);

        for (int i = 0; i < 4; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){

            }
        }

        assertEquals("Speed", snnope.getSnakes().get(0).getPoder().getTipo());
    }

    @Test
    void esRecogidaSpeedSlowTest() {
        snnope.setPowerUp(new Speed(snnope));
        Integer[] coordPrueba = {4,0};
        snnope.getPowerUp().setCoordenadas(coordPrueba);

        for (int i = 0; i < 4; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){

            }
        }

        assertEquals("Speed", snnope.getSnakes().get(0).getPoder().getTipo());
    }

    @Test
    void esRecogidaBloqueTest() {
        snnope.setPowerUp(new Bloque(snnope));
        Integer[] coordPrueba = {4,0};
        snnope.getPowerUp().setCoordenadas(coordPrueba);

        for (int i = 0; i < 4; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){

            }
        }

        assertEquals("Bloque", snnope.getSnakes().get(0).getPoder().getTipo());
    }

    @Test
    void esRecogidaDivisionTest() {
        snnope.setPowerUp(new Division(snnope));
        Integer[] coordPrueba = {4,0};
        snnope.getPowerUp().setCoordenadas(coordPrueba);

        for (int i = 0; i < 4; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){

            }
        }

        assertEquals("Division", snnope.getSnakes().get(0).getPoder().getTipo());
    }

    @Test
    void esRecogidaLupaTest() {
        snnope.setPowerUp(new Lupa(snnope));
        Integer[] coordPrueba = {4,0};
        snnope.getPowerUp().setCoordenadas(coordPrueba);

        for (int i = 0; i < 4; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){

            }
        }

        assertEquals("Lupa", snnope.getSnakes().get(0).getPoder().getTipo());
    }

    @Test
    void esRecogidaFuegoTest() {
        snnope.setPowerUp(new Estrella(snnope));
        Integer[] coordPrueba = {4,0};
        snnope.getPowerUp().setCoordenadas(coordPrueba);

        for (int i = 0; i < 5; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){

            }
        }

        assertEquals("Fuego", snnope.getSnakes().get(0).getPoder().getTipo());
    }


    @Test
    void esUsadaSpeedFastTest() {
        snnope.setPowerUp(new Speed(snnope));
        Integer[] coordPrueba = {4,0};
        snnope.getPowerUp().setCoordenadas(coordPrueba);

        for (int i = 0; i < 4; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){

            }
        }

        snnope.getSnakes().get(0).usaPowerUp();

        assertNull(snnope.getSnakes().get(0).getPoder());
    }

    @Test
    void esUsadaSpeedSlowTest() {
        snnope.setPowerUp(new Speed(snnope));
        Integer[] coordPrueba = {4,0};
        snnope.getPowerUp().setCoordenadas(coordPrueba);

        for (int i = 0; i < 4; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){

            }
        }

        snnope.getSnakes().get(0).usaPowerUp();

        assertNull(snnope.getSnakes().get(0).getPoder());
    }

    @Test
    void esUsadaBloqueTest() {
        snnope.setPowerUp(new Bloque(snnope));
        Integer[] coordPrueba = {4,0};
        snnope.getPowerUp().setCoordenadas(coordPrueba);

        for (int i = 0; i < 4; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){

            }
        }

        snnope.getSnakes().get(0).usaPowerUp();

        assertNull(snnope.getSnakes().get(0).getPoder());
        assertEquals(1, snnope.getBloques().size());
    }

    @Test
    void esUsadaDivisionTest() {
        snnope.setPowerUp(new Division(snnope));
        Integer[] coordPrueba = {4,0};
        snnope.getPowerUp().setCoordenadas(coordPrueba);

        for (int i = 0; i < 4; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){

            }
        }

        snnope.getSnakes().get(0).usaPowerUp();

        assertNull(snnope.getSnakes().get(0).getPoder());
        assertEquals(2, snnope.getSnakes().get(0).getSnake().size());
    }

    @Test
    void esUsadaLupaTest() {
        snnope.setPowerUp(new Lupa(snnope));
        Integer[] coordPrueba = {4,0};
        snnope.getPowerUp().setCoordenadas(coordPrueba);

        for (int i = 0; i < 4; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){

            }
        }

        snnope.getSnakes().get(0).usaPowerUp();

        assertNull(snnope.getSnakes().get(0).getPoder());
    }

    @Test
    void esUsadaFuegoTest() {
        snnope.setPowerUp(new Estrella(snnope));
        Integer[] coordPrueba = {4,0};
        snnope.getPowerUp().setCoordenadas(coordPrueba);
        Integer[] bloque = {6,0};
        snnope.addBloque(bloque);

        for (int i = 0; i < 4; i++) {
            try {
                snnope.getSnakes().get(0).avanzar();
            }catch (SnOOPeException e){

            }
        }

        snnope.getSnakes().get(0).usaPowerUp();

        assertNull(snnope.getSnakes().get(0).getPoder());
    }

    
}
