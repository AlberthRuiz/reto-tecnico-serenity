package com.prueba.tecnica.questions.api;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;

public class ResponseObjects implements Question<List<Map<String, Object>>> {
    private final String jsonPath;

    private ResponseObjects(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public static ResponseObjects at(String jsonPath) {
        return new ResponseObjects(jsonPath);
    }

    @Override
    public List<Map<String, Object>> answeredBy(Actor actor) {
        return SerenityRest.lastResponse().jsonPath().getList(jsonPath);
    }
}