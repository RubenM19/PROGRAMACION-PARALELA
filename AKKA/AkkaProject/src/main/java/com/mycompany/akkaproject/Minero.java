/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.akkaproject;

import akka.actor.UntypedActor;
import akka.event.LoggingAdapter;

/**
 *
 * @author Rubén
 */
public class Minero extends UntypedActor {
    public enum Mensaje {
        OBTENER_MATERIALES
    }
 
    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private static final long TIEMPO_OBTENCION_MATERIALES = 2000;
 
    @Override
    public void onReceive(Object o) throws InterruptedException {
        log.info("[Minero] ha recibido el mensaje: \"{}\".", o);
 
        if (o == Mensaje.OBTENER_MATERIALES) {
            log.info("[Minero] está obteniendo materiales...");
            obtenerMinerales();
            log.info("[Minero] ha obtenido materiales.");
        }
    }
 
    private void obtenerMinerales() throws InterruptedException {
        Thread.sleep(TIEMPO_OBTENCION_MATERIALES);
    }
}
