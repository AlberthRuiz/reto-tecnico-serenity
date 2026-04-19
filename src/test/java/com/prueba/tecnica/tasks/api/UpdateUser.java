package com.prueba.tecnica.tasks.api;

import com.prueba.tecnica.abilities.CallReqresApi;
import com.prueba.tecnica.models.UserRequest;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class UpdateUser implements Task {

    private final int id;
    private final UserRequest user;

    public UpdateUser(int id, UserRequest user) {
        this.id = id;
        this.user = user;
    }

    public static UpdateUser withIdAndData(int id, String name, String job) {
        return new UpdateUser(id, new UserRequest(name, job));
    }

    @Override
    @Step("{0} actualiza el usuario #id con nombre '#user.name' y trabajo '#user.job'")
    public <T extends Actor> void performAs(T actor) {
        CallReqresApi.as(actor)
                .request()
                .body(user)
                .put("/api/users/" + id);
    }
}