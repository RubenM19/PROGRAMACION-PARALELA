// Rubén Alberto Mondragón Zúñiga 20200082
// Anthony Joel Llactahuaman Muguerza 20200091

package demo.ejercicioakka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

/**
 *
 * @author Rubén
 */
public class ActorMaestro extends AbstractActor{
    private final double[] vector;
    private final int numBloques;
    private final int elementosPorBloque;

    private double sumaTotal = 0.0;
    private int resultadosRecibidos = 0;

    private final ActorRef actorBloque = getContext().actorOf(Props.create(ActorBloque.class));

    public ActorMaestro(double[] vector, int numBloques) {
        this.vector = vector;
        this.numBloques = numBloques;
        this.elementosPorBloque = vector.length / numBloques;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("Comenzar", mensaje -> {
                    for (int i = 0; i < numBloques; i++) {
                        int inicio = i * elementosPorBloque;
                        int fin = (i + 1) * elementosPorBloque;
                        actorBloque.tell(new CalcularSuma(vector, inicio, fin), getSelf());
                    }
                })
                .match(ResultadoSuma.class, resultadoSuma -> {
                    sumaTotal += resultadoSuma.getSuma();
                    resultadosRecibidos++;

                    if (resultadosRecibidos == numBloques) {
                        System.out.println("La suma total es: " + sumaTotal);
                        getContext().getSystem().terminate();
                    }
                })
                .build();
    }
}
