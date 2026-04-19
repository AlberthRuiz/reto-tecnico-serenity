package com.prueba.tecnica.abilities;

import com.prueba.tecnica.utils.Config;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class CallReqresApi implements Ability {
    private CallReqresApi() {
    }

    public static CallReqresApi asApiClient() {
        return new CallReqresApi();
    }

    public static CallReqresApi as(Actor actor) {
        return actor.abilityTo(CallReqresApi.class);
    }

    public RequestSpecification request() {
        return SerenityRest.given()
                .baseUri(Config.apiBaseUrl())
                .header("x-api-key", Config.reqresApiKey())
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

    }

}
