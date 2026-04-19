package com.prueba.tecnica.tasks.api;

import com.prueba.tecnica.abilities.CallReqresApi;
import com.prueba.tecnica.models.UserRequest;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class CreateUser implements Task {

    private final UserRequest user;

    public CreateUser(UserRequest user) {
        this.user = user;
    }

    public static CreateUser withNameAndJob(String name, String job) {
        return new CreateUser(new UserRequest(name, job));
    }

    @Override
    @Step("{0} crea un usuario con nombre '#user.name' y trabajo '#user.job'")
    public <T extends Actor> void performAs(T actor) {
        CallReqresApi.as(actor)
                .request()
                .body(user)
                .post("/api/users");
    }
}