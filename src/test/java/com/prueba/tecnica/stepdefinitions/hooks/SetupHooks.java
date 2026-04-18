package com.prueba.tecnica.stepdefinitions.hooks;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SetupHooks {
    static {
        Logger.getLogger("com.google.common.eventbus").setLevel(Level.OFF);
        System.setErr(new PrintStream(OutputStream.nullOutputStream()));
    }

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new Cast());
    }
}
