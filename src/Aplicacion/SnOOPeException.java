package Aplicacion;

public class SnOOPeException extends Exception{
    public static final String GAME_OVER= "Has ingerido veneno";
    public static final String GAME_OVER_WALL = "Te has estrellado";
    public static final String GAME_OVER_DULCES = "Has comido muchos dulces";
    public static final String GAME_OVER_SUICIDIO = "Te has mordido a ti mismo";
    public static final String ERROR_TIPO_GUARDAR = "Los archivos a guardar deben ser '.dat'";
    public static final String ERROR_GUARDAR = "Ocurrio un error al guardar el archivo";
    public static final String ERROR_TIPO_ABRIR = "Los archivos a abrir deben ser '.dat'";
    public static final String ERROR_ABRIR = "Ocurrio un error al abrir el archivo";
    public static final String LA_MATO = "Las serpientes se han chocado";

    public SnOOPeException(String message) {
        super(message);
    }
}
