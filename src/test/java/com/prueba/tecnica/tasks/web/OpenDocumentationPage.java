package com.prueba.tecnica.tasks.web;

import com.prueba.tecnica.ui.DocumentationPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.playwright.interactions.Open;

public class OpenDocumentationPage implements Task {
    public static OpenDocumentationPage directly() {
        return new OpenDocumentationPage();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(DocumentationPage.url()));
    }
}
