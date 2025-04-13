import java.awt.*;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.*;

public class Hanoi extends JPanel {
    private static final int ANCHO_TORRE = 50;
    private static final int ALTO_TORRE = 150;
    private static final int ESPACIO_TORRES = 150;
    private Stack<Integer>[] torres;
    private int discos;
    private Scanner scanner;

    public static void main(String[] args) {
        // Inicializamos el número de discos
        int discos = 5;

        // Creamos las torres y las inicializamos
        Hanoi panel = new Hanoi(discos);
        JFrame frame = new JFrame("Torres de Hanoi");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);

        // Llamamos a la función recursiva de las Torres de Hanoi
        panel.torresHanoi(discos, 0, 1, 2);

        // Actualizamos la visualización después de todos los movimientos
        panel.repaint();
    }

    public Hanoi(int discos) {
        this.discos = discos;
        this.scanner = new Scanner(System.in);
        // Inicializamos las torres (3 torres)
        torres = new Stack[3];
        for (int i = 0; i < 3; i++) {
            torres[i] = new Stack<>();
        }
        // Colocamos todos los discos en la primera torre (Torre 0)
        for (int i = discos; i >= 1; i--) {
            torres[0].push(i);
        }
    }

    // Método recursivo para resolver las Torres de Hanoi
    public void torresHanoi(int discos, int torre1, int torre2, int torre3) {
        if (discos == 1) {
            moverDisco(torre1, torre3);
        } else {
            torresHanoi(discos - 1, torre1, torre3, torre2);
            moverDisco(torre1, torre3);
            torresHanoi(discos - 1, torre2, torre1, torre3);
        }
    }

    // Método para mover el disco de una torre a otra
    private void moverDisco(int torreOrigen, int torreDestino) {
        // Movemos el disco de la torre origen a la torre destino
        int disco = torres[torreOrigen].pop();
        torres[torreDestino].push(disco);
        // Actualizamos la interfaz gráfica
        repaint();

        // Mostramos el estado actual de las torres
        System.out.println("Estado actual de las torres:");
        System.out.println("Torre 1: " + torres[0]);
        System.out.println("Torre 2: " + torres[1]);
        System.out.println("Torre 3: " + torres[2]);

        // Preguntamos si el usuario quiere continuar
        System.out.println("Presiona Enter para continuar al siguiente movimiento...");
        scanner.nextLine();
    }

    // Dibujamos las torres y los discos en la ventana
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujamos las torres
        g.setColor(Color.BLACK);
        for (int i = 0; i < 3; i++) {
            g.fillRect(i * ESPACIO_TORRES + 100, 250, ANCHO_TORRE, ALTO_TORRE);
        }

        // Dibujamos los discos
        for (int i = 0; i < 3; i++) {
            int yOffset = 0;
            for (int j = 0; j < torres[i].size(); j++) {
                int disco = torres[i].get(j);
                g.setColor(Color.BLUE);
                g.fillRect(i * ESPACIO_TORRES + 100 - disco * 10, 250 - yOffset, disco * 20, 20);
                yOffset += 25;
            }
        }
    }
}
