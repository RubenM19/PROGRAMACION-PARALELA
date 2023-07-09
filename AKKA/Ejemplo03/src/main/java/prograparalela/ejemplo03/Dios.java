package prograparalela.ejemplo03;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 *
 * @author Rubén Mondragón
 */
public class Dios extends UntypedAbstractActor{
    
    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private int cuenta = 0;
    
    public enum Mensaje{
        START,
        ESPADA_LISTA
    }
    
    @Override
    public void onReceive(Object message) throws Throwable{
        log.info("[dios] ha recibido el mensaje: \"{}\".", message);
        if (message == Mensaje.START){
            cuenta = 2;
            Ejemplo03.espadachin1.tell(Espadachin.Mensaje.ESPADA_ROTA, ActorRef.noSender());
            Ejemplo03.espadachin2.tell(Espadachin.Mensaje.ESPADA_ROTA, ActorRef.noSender());
        } else if (message == Mensaje.ESPADA_LISTA){
            cuenta--;
            if(cuenta<1){
                Ejemplo03.actorSystem.terminate();
            }
        }else{
            unhandled(message);
        }
    }
}
