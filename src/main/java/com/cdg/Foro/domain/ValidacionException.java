package com.cdg.Foro.domain;

public class ValidacionException extends RuntimeException {
    public ValidacionException(String message) {
        super(message);//enviar el mensaje  a la superclase RuntimeException
    }
}

