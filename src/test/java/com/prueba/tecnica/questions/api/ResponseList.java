package com.prueba.tecnica.questions.api;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;

public class ResponseList implements Question<List<String>> {
    private final String jsonPath;

    private ResponseList(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public static ResponseList stringsAt(String jsonPath) {
        return new ResponseList(jsonPath);
    }

    @Override
    public List<String> answeredBy(Actor actor) {
        return SerenityRest.lastResponse().jsonPath().getList(jsonPath, String.class);
    }
}