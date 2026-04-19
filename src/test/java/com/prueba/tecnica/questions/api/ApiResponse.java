package com.prueba.tecnica.questions.api;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import java.util.List;
import java.util.Map;

public class ApiResponse implements Question<Response> {

    public static ApiResponse received() {
        return new ApiResponse();
    }

    @Override
    public Response answeredBy(Actor actor) {
        return SerenityRest.lastResponse();
    }

    public static Question<Integer> statusCode() {
        return ResponseStatusCode.value();
    }

    public static Question<String> field(String jsonPath) {
        return ResponseField.at(jsonPath);
    }

    public static Question<List<String>> stringList(String jsonPath) {
        return ResponseList.stringsAt(jsonPath);
    }

    public static Question<List<Map<String, Object>>> objectList(String jsonPath) {
        return ResponseObjects.at(jsonPath);
    }

    public static Question<Integer> headStatusCode(String url) {
        return HeadStatusCode.of(url);
    }
}