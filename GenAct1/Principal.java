// Principal.java
public class Principal {
    public static void main(String[] args) {
        Bolsa<Chocolatina> bolsaChoco = new Bolsa<>(3);

        bolsaChoco.add(new Chocolatina("Milka"));
        bolsaChoco.add(new Chocolatina("Ferrero"));
        bolsaChoco.add(new Chocolatina("Sublime"));

        for (Chocolatina choco : bolsaChoco) {
            System.out.println("Chocolatina: " + choco.getMarca());
        }

        // Prueba con Golosina
        Bolsa<Golosina> bolsaGolo = new Bolsa<>(2);
        bolsaGolo.add(new Golosina("Gomita", 0.05));
        bolsaGolo.add(new Golosina("Caramelo", 0.02));

        for (Golosina g : bolsaGolo) {
            System.out.println("Golosina: " + g.getNombre() + " - " + g.getPeso() + "kg");
        }
    }
}
