package com.prueba.tecnica.questions.api;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseField implements Question<String> {
    private final String jsonPath;

    private ResponseField(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public static ResponseField at(String jsonPath) {
        return new ResponseField(jsonPath);
    }

    @Override
    public String answeredBy(Actor actor) {
        return SerenityRest.lastResponse().jsonPath().getString(jsonPath);
    }
}
