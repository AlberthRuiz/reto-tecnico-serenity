package com.prueba.tecnica.tasks.api;

import com.prueba.tecnica.abilities.CallReqresApi;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class CreateUserWithEmptyBody implements Task {

    public static CreateUserWithEmptyBody attempt() {
        return new CreateUserWithEmptyBody();
    }

    @Override
    @Step("{0} envia una solicitud POST sin body al endpoint de usuarios")
    public <T extends Actor> void performAs(T actor) {
        CallReqresApi.as(actor)
                .request()
                .post("/api/users");
    }
}