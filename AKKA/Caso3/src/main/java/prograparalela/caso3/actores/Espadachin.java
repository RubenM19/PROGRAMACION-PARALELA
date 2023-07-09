package prograparalela.caso3.actores;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import prograparalela.caso3.Caso3;

/**
 *
 * @author Rubén
 */
public class Espadachin extends UntypedActor {
    public enum Mensaje {
        ESPADA_NUEVA,
        ESPADA_ROTA
    }
 
    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
 
    @Override
    public void onReceive(Object o) {
        log.info("[Espadachin] ha recibido el mensaje: \"{}\".", o);
 
        if (o == Mensaje.ESPADA_ROTA) {
            Caso3.herrero.tell(Herrero.Mensaje.CREAR_ESPADA, getSelf());
        } else if (o == Mensaje.ESPADA_NUEVA) {
            getContext().stop(getSelf());
        } else {
            unhandled(o);
        }
    }
}
