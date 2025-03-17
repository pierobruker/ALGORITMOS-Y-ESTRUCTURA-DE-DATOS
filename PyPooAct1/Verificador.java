public class Verificador {

    // Método para verificar si dos rectángulos se sobreponen
    public static boolean seSobreponen(Rectangulo r1, Rectangulo r2) {
        Coordenada r1Esq1 = r1.getEsquina1();
        Coordenada r1Esq2 = r1.getEsquina2();
        Coordenada r2Esq1 = r2.getEsquina1();
        Coordenada r2Esq2 = r2.getEsquina2();
    
        // Verificar si no hay superposición
        boolean noSobreponen = r1Esq2.getX() <= r2Esq1.getX() || r2Esq2.getX() <= r1Esq1.getX() ||
                               r1Esq2.getY() <= r2Esq1.getY() || r2Esq2.getY() <= r1Esq1.getY();
    
        return !noSobreponen;
    }

    // Método para verificar si dos rectángulos están juntos
    public static boolean estanJuntos(Rectangulo r1, Rectangulo r2) {
            Coordenada r1Esq1 = r1.getEsquina1();
            Coordenada r1Esq2 = r1.getEsquina2();
            Coordenada r2Esq1 = r2.getEsquina1();
            Coordenada r2Esq2 = r2.getEsquina2();
        
            // Verificar si los rectángulos comparten un borde o una esquina
            boolean tocanX = r1Esq2.getX() == r2Esq1.getX() || r2Esq2.getX() == r1Esq1.getX();
            boolean tocanY = r1Esq2.getY() == r2Esq1.getY() || r2Esq2.getY() == r1Esq1.getY();
        
            return tocanX || tocanY;
        }
    // Método para verificar si dos rectángulos son disjuntos
    public static boolean sonDisjuntos(Rectangulo r1, Rectangulo r2) {
        return !seSobreponen(r1, r2) && !estanJuntos(r1, r2);
    }
}
