package com.prueba.tecnica.questions.api;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class HeadStatusCode implements Question<Integer> {
    private final String url;

    private HeadStatusCode(String url) {
        this.url = url;
    }

    public static HeadStatusCode of(String url) {
        return new HeadStatusCode(url);
    }

    @Override
    public Integer answeredBy(Actor actor) {
        return SerenityRest.head(url).getStatusCode();
    }
}