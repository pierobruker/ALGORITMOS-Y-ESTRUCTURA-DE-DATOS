import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorDeTareas<Tarea> gestor = new GestorDeTareas<>();
        List<Tarea> tareasCompletadas = new ArrayList<>();

        // Datos de prueba iniciales
        gestor.agregarTarea(new Tarea("Hacer tarea", 5));
        gestor.agregarTarea(new Tarea("Presentar informe", 8));

        while (true) {
            System.out.println("\n--- Menú de operaciones ---");
            System.out.println("1. Agregar una tarea");
            System.out.println("2. Eliminar una tarea");
            System.out.println("3. Ver todas las tareas");
            System.out.println("4. Contar las tareas");
            System.out.println("5. Invertir el orden de las tareas");
            System.out.println("6. Verificar si una tarea existe");
            System.out.println("7. Obtener la tarea más prioritaria");
            System.out.println("8. Marcar tarea como completada");
            System.out.println("9. Ver tareas completadas");
            System.out.println("10. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Descripción: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Prioridad (1-10): ");
                    int prioridad = scanner.nextInt(); scanner.nextLine();
                    gestor.agregarTarea(new Tarea(descripcion, prioridad));
                    break;

                case 2:
                    System.out.print("Descripción de tarea a eliminar: ");
                    descripcion = scanner.nextLine();
                    System.out.print("Prioridad: ");
                    prioridad = scanner.nextInt(); scanner.nextLine();
                    boolean eliminada = gestor.eliminarTarea(new Tarea(descripcion, prioridad));
                    System.out.println(eliminada ? "Tarea eliminada." : "No se encontró la tarea.");
                    break;

                case 3:
                    System.out.println("Tareas actuales:");
                    gestor.imprimirTareas();
                    break;

                case 4:
                    System.out.println("Total de tareas: " + gestor.contarTareas());
                    break;

                case 5:
                    gestor.invertirTareas();
                    System.out.println("Tareas invertidas.");
                    break;

                case 6:
                    System.out.print("Descripción a buscar: ");
                    descripcion = scanner.nextLine();
                    System.out.print("Prioridad: ");
                    prioridad = scanner.nextInt(); scanner.nextLine();
                    boolean existe = gestor.contieneTarea(new Tarea(descripcion, prioridad));
                    System.out.println(existe ? "Tarea existe." : "No se encontró.");
                    break;

                case 7:
                    Tarea tareaTop = gestor.obtenerTareaMasPrioritaria();
                    System.out.println(tareaTop != null ? tareaTop : "No hay tareas.");
                    break;

                case 8:
                    System.out.print("Descripción de tarea completada: ");
                    descripcion = scanner.nextLine();
                    System.out.print("Prioridad: ");
                    prioridad = scanner.nextInt(); scanner.nextLine();
                    Tarea tareaCompletada = new Tarea(descripcion, prioridad);
                    if (gestor.eliminarTarea(tareaCompletada)) {
                        tareasCompletadas.add(tareaCompletada);
                        System.out.println("Tarea marcada como completada.");
                    } else {
                        System.out.println("Tarea no encontrada.");
                    }
                    break;

                case 9:
                    System.out.println("Tareas completadas:");
                    for (Tarea t : tareasCompletadas) {
                        System.out.println(t);
                    }
                    break;

                case 10:
                    System.out.println("Saliendo del programa.");
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
