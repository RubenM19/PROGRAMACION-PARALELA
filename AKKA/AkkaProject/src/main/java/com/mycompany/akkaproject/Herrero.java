/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.akkaproject;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.LoggingAdapter;

/**
 *
 * @author Rubén
 */
public class Herrero extends UntypedActor {
    public enum Mensaje {
        CREAR_ESPADA
    }
 
    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef minero;
 
 
    @Override
    public void preStart() {
        minero = getContext().actorOf(Props.create(Minero.class), "minero");
    }
 
    @Override
    public void onReceive(Object o) throws InterruptedException {
        log.info("[Herrero] ha recibido el mensaje: \"{}\".", o);
 
        if (o == Mensaje.CREAR_ESPADA) {
            minero.tell(Minero.Mensaje.OBTENER_MATERIALES, getSelf());
        }
    }
}
