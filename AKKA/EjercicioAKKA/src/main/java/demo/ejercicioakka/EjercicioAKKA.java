// Rubén Alberto Mondragón Zúñiga 20200082
// Anthony Joel Llactahuaman Muguerza 20200091

package demo.ejercicioakka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import java.util.Random;

/**
 *
 * @author Rubén
 */
public class EjercicioAKKA {

    public static void main(String[] args) {
        // Crear el vector de 100,000 números de punto flotante con valores aleatorios
        double[] vector = new double[100000];
        Random random = new Random();
        for (int i = 0; i < vector.length; i++) {
            vector[i] = random.nextDouble();
        }

        // Crear el sistema de actores
        final ActorSystem system = ActorSystem.create("SumaVector");
        final ActorRef actorMaestro = system.actorOf(Props.create(ActorMaestro.class, vector, 4), "actorMaestro");

        // Iniciar el proceso de cálculo
        actorMaestro.tell("Comenzar", ActorRef.noSender());
    }
}
