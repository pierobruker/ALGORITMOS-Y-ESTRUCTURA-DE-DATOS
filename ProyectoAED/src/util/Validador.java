package util;

public class Validador {

    // Validar IDs (alfanumérico)
    public static boolean validarId(String id) {
        return id != null && id.matches("^[a-zA-Z0-9]{3,20}$");
    }

    // Validar nombres (letras, espacios y algunos caracteres especiales)
    public static boolean validarNombre(String nombre) {
        return nombre != null && nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s\\-']{3,50}$");
    }

    // Validar tipo de ubicación
    public static boolean validarTipoUbicacion(String tipo) {
        return tipo != null && 
               (tipo.equals("ESTANTERIA") || 
                tipo.equals("PASILLO") || 
                tipo.equals("CARGA"));
    }

    // Validar categoría
    public static boolean validarCategoria(String categoria) {
        return categoria != null && categoria.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{3,30}$");
    }

    // Validar stock
    public static boolean validarStock(int stock) {
        return stock >= 0 && stock <= 10000;
    }

    // Validar precio
    public static boolean validarPrecio(double precio) {
        return precio >= 0.01 && precio <= 1000000;
    }

    // Validar peso de ruta
    public static boolean validarPesoRuta(double peso) {
        return peso > 0 && peso <= 1000;
    }
}