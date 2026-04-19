package com.prueba.tecnica.tasks.api;

import com.prueba.tecnica.abilities.CallReqresApi;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class GetUserById implements Task {

    private final int id;

    public GetUserById(int id) {
        this.id = id;
    }

    public static GetUserById withId(int id) {
        return new GetUserById(id);
    }

    @Override
    @Step("{0} solicita el usuario con id #id")
    public <T extends Actor> void performAs(T actor) {
        CallReqresApi.as(actor)
                .request()
                .get("/api/users/" + id);
    }
}
