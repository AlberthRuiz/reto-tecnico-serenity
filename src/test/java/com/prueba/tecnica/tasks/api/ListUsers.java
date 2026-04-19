package com.prueba.tecnica.tasks.api;

import com.prueba.tecnica.abilities.CallReqresApi;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class ListUsers implements Task {
    private final int page;

    public ListUsers(int page) {
        this.page = page;
    }

    public static ListUsers onPage(int page) {
        return new ListUsers(page);
    }


    @Override
    @Step("{0} solicita el listado de usuarios en la pagina #page")
    public <T extends Actor> void performAs(T actor) {
        CallReqresApi.as(actor)
                .request()
                .queryParam("page", page)
                .get("/api/users");
    }
}
