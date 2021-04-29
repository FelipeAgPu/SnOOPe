package Aplicacion;

public class SnOOPeException extends Exception{
    public static final String GAME_OVER = "Game Over";

    public SnOOPeException(String message) {
        super(message);
    }
}
