package com.tartur.capco.application.cli;

public class NoActiveSession extends RuntimeException {
    public NoActiveSession() {
        super("You should start new session");
    }
}
