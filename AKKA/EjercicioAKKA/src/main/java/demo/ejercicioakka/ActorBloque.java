// Rubén Alberto Mondragón Zúñiga 20200082
// Anthony Joel Llactahuaman Muguerza 20200091

package demo.ejercicioakka;

import akka.actor.AbstractActor;
import java.util.Arrays;

/**
 *
 * @author Rubén
 */
public class ActorBloque extends AbstractActor{
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(CalcularSuma.class, calcularSuma -> {
                    double suma = Arrays.stream(calcularSuma.getVector(), calcularSuma.getInicio(), calcularSuma.getFin())
                            .sum();
                    getSender().tell(new ResultadoSuma(suma), getSelf());
                })
                .build();
    }
}
