package Prueba;
import controlador.ControladorAlmacen;

public class Main {
    public static void main(String[] args) {
        System.out.println("================================================");
        System.out.println(" SISTEMA DE GESTIÓN Y OPTIMIZACIÓN DE ALMACENES ");
        System.out.println("================================================");
        System.out.println("   Modelado con Grafos y Árboles B+ - Java");
        System.out.println("================================================");
        
        ControladorAlmacen controlador = new ControladorAlmacen();
        controlador.iniciarSistema();
        
        System.out.println("================================================");
        System.out.println("       APLICACIÓN FINALIZADA - HASTA PRONTO");
        System.out.println("================================================");
    }
}