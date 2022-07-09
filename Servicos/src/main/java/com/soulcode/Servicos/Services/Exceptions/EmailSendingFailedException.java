package com.soulcode.Servicos.Services.Exceptions;

public class EmailSendingFailedException extends RuntimeException {

    public EmailSendingFailedException(String message) {
        super(message);
    }
}
