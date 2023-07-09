package prograparalela.ejemplo03;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 *
 * @author Rubén Mondragón
 */
public class Ejemplo03 {
    
    public static ActorRef dios;
    public static ActorRef espadachin1;
    public static ActorRef espadachin2;
    public static ActorRef herrero;
    public static ActorRef minero;
    public static ActorSystem actorSystem;
    
    public static void main(String[] args) throws InterruptedException {
        actorSystem = ActorSystem.create("ActorSystem");
        espadachin1 = actorSystem.actorOf(Props.create(Espadachin.class), "espadachin1");
        espadachin2 = actorSystem.actorOf(Props.create(Espadachin.class), "espadachin2");
        herrero = actorSystem.actorOf(Props.create(Herrero.class), "herrero");
        minero = actorSystem.actorOf(Props.create(Minero.class), "minero");
        dios = actorSystem.actorOf(Props.create(Dios.class), "dios");
        
        dios.tell(Dios.Mensaje.START, ActorRef.noSender());
    }
}
