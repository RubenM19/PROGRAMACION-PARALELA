package prograparalela.ejemplo03;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import java.util.ArrayList;

/**
 *
 * @author Rubén Mondragón
 */
public class Herrero extends UntypedAbstractActor {
    public enum Mensaje {
        CREAR_ESPADA,
        MATERIALES
    }
 
    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
 
    private static final long TIEMPO_CREACION_ESPADA = 2000;
    private ArrayList<ActorRef> espadachines;
 
 
    @Override
    public void preStart() {
        espadachines = new ArrayList<>();
    }
 
    @Override
    public void onReceive(Object o) throws InterruptedException {
        log.info("[Herrero] ha recibido el mensaje: \"{}\".", o);
 
        if (o == Mensaje.CREAR_ESPADA) {
            espadachines.add(getSender());
            Ejemplo03.minero.tell(Minero.Mensaje.OBTENER_MATERIALES, getSelf());
        } else if (o == Mensaje.MATERIALES) {
            log.info("[Herrero] está creando espada...");
            crearEspada();
            log.info("[Herrero] ha creado espada.");
            if (!espadachines.isEmpty()) {
                espadachines.get(0).tell(Espadachin.Mensaje.ESPADA_NUEVA, getSelf());
                espadachines.remove(0);
            }
        } else {
            unhandled(o);
        }
    }
 
    private void crearEspada() throws InterruptedException {
        Thread.sleep(TIEMPO_CREACION_ESPADA);
    }
}
