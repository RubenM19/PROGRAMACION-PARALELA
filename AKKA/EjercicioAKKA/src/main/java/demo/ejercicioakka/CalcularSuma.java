// Rubén Alberto Mondragón Zúñiga 20200082
// Anthony Joel Llactahuaman Muguerza 20200091

package demo.ejercicioakka;

/**
 *
 * @author Rubén
 */
public class CalcularSuma {
    private final double[] vector;
    private final int inicio;
    private final int fin;

    public CalcularSuma(double[] vector, int inicio, int fin) {
        this.vector = vector;
        this.inicio = inicio;
        this.fin = fin;
    }

    public double[] getVector() {
        return vector;
    }

    public int getInicio() {
        return inicio;
    }

    public int getFin() {
        return fin;
    }
}


