package prograparalela.ejemplo03;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 *
 * @author Rubén Mondragón
 */
public class Espadachin extends UntypedAbstractActor {
    public enum Mensaje {
        ESPADA_NUEVA,
        ESPADA_ROTA
    }
 
    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
 
    @Override
    public void onReceive(Object o) {
        log.info("[Espadachin] ha recibido el mensaje: \"{}\".", o);
 
        if (o == Mensaje.ESPADA_ROTA) {
            Ejemplo03.herrero.tell(Herrero.Mensaje.CREAR_ESPADA, getSelf());
        } else if (o == Mensaje.ESPADA_NUEVA) {
            Ejemplo03.dios.tell(Dios.Mensaje.ESPADA_LISTA,getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(o);
        }
    }
}