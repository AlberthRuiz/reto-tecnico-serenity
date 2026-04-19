package com.prueba.tecnica.tasks.web;

import com.prueba.tecnica.ui.HomePage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.playwright.interactions.Click;

public class NavigateToDocumentation implements Task {
    public static NavigateToDocumentation fromHomePage() {
        return new NavigateToDocumentation();
    }

    @Override
    @Step("{0} hace clic en el enlace Documentation")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(HomePage.DOCUMENTATION_LINK));
    }
}
