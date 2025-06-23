package controlador;

import modelo.Almacen; 
import modelo.Categoria;
import modelo.Producto;
import util.EntradaUsuario;

public class ControladorArbolB {

 // El controlador tiene acceso al almacén para gestionar los productos
 private final Almacen almacen;

 // Constructor que recibe la instancia del almacén
 public ControladorArbolB(Almacen almacen) {
     this.almacen = almacen;
 }

 // Método principal que muestra el menú de gestión de productos
 public void gestionarProductos() {
     while (true) {
         int opcion = EntradaUsuario.leerEntero(
             "\n==== GESTIÓN DE PRODUCTOS ====\n" +
             "1. Agregar producto\n" +
             "2. Buscar producto\n" +
             "3. Eliminar producto\n" +
             "4. Listar productos por categoría\n" +
             "5. Agregar categoría\n" +
             "6. Volver\n" +
             "Seleccione: ", 1, 6);
         
         // Según la opción elegida, se llama al método correspondiente
         switch (opcion) {
             case 1 -> agregarProducto();
             case 2 -> buscarProducto();
             case 3 -> eliminarProducto();
             case 4 -> listarProductosCategoria();
             case 5 -> agregarCategoria();
             case 6 -> { return; }
         }
     }
 }

 // Opción 1: Agregar un nuevo producto al almacén
 private void agregarProducto() {
     System.out.println("\n--- AGREGAR PRODUCTO ---");
     // Se piden todos los datos necesarios del producto
     String id = EntradaUsuario.leerString("ID Producto: ");
     String nombre = EntradaUsuario.leerString("Nombre: ");
     String categoria = EntradaUsuario.leerString("Categoría: ");
     int stock = EntradaUsuario.leerEntero("Stock inicial: ", 0, Integer.MAX_VALUE);
     double precio = EntradaUsuario.leerDouble("Precio: ", 0, Double.MAX_VALUE);
     
     // Se crea el objeto producto con los datos ingresados
     Producto producto = new Producto(id, nombre, categoria, stock, precio);
     // Se intenta agregar el producto al almacén
     if (almacen.agregarProducto(producto)) {
         System.out.println("Producto agregado exitosamente!");
     } else {
         System.out.println("Error: Categoría no existe o ID duplicado");
     }
 }

 // Opción 2: Buscar un producto existente por su ID
 private void buscarProducto() {
     System.out.println("\n--- BUSCAR PRODUCTO ---");
     String id = EntradaUsuario.leerString("ID Producto: ");
     Producto producto = almacen.buscarProducto(id);
     // Si se encuentra el producto, se muestra su información
     if (producto != null) {
         System.out.println("Producto encontrado:\n" + producto);
     } else {
         System.out.println("Producto no encontrado");
     }
 }

 // Opción 3: Eliminar un producto por su ID
 private void eliminarProducto() {
     System.out.println("\n--- ELIMINAR PRODUCTO ---");
     String id = EntradaUsuario.leerString("ID Producto a eliminar: ");
     if (almacen.eliminarProducto(id)) {
         System.out.println("Producto eliminado exitosamente!");
     } else {
         System.out.println("Error: Producto no encontrado");
     }
 }

 // Opción 4: Listar todos los productos de una categoría específica
 private void listarProductosCategoria() {
     System.out.println("\n--- LISTAR PRODUCTOS POR CATEGORÍA ---");
     String nombreCategoria = EntradaUsuario.leerString("Nombre categoría: ");
     Categoria categoria = almacen.getCategoria(nombreCategoria);
     
     // Si la categoría existe, se listan sus productos
     if (categoria != null) {
         System.out.println("\nProductos en categoría '" + nombreCategoria + "':");
         categoria.listarProductos().forEach(System.out::println);
     } else {
         System.out.println("Categoría no encontrada");
     }
 }

 // Opción 5: Agregar una nueva categoría al sistema
 private void agregarCategoria() {
     System.out.println("\n--- AGREGAR CATEGORÍA ---");
     String nombre = EntradaUsuario.leerString("Nombre categoría: ");
     int orden = EntradaUsuario.leerEntero("Orden árbol B+ (mínimo 3): ", 3, Integer.MAX_VALUE);
     
     // Se agrega la categoría con el orden indicado para el árbol B+
     if (almacen.agregarCategoria(nombre, orden)) {
         System.out.println("Categoría creada exitosamente!");
     } else {
         System.out.println("Error: Categoría ya existe");
     }
 }
}
