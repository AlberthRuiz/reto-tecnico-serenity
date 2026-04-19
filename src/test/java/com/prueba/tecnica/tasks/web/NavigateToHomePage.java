package com.prueba.tecnica.tasks.web;

import com.prueba.tecnica.ui.HomePage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.playwright.interactions.Open;

public class NavigateToHomePage implements Task {

    public static NavigateToHomePage ofSelenium(){
        return new NavigateToHomePage();
    }

    @Override
    @Step("{0} navega a la página principal de Selenium")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(HomePage.url()));
    }
}
