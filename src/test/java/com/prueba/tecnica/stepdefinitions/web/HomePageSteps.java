package com.prueba.tecnica.stepdefinitions.web;

import com.prueba.tecnica.questions.web.CurrentUrl;
import com.prueba.tecnica.questions.web.PageTitle;
import com.prueba.tecnica.tasks.web.NavigateToHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;

public class HomePageSteps {

    @Given("{word} quiere validar la pagina principal de Selenium")
    public void quiereValidarPaginaPrincipal(String actorName) {
        OnStage.theActorCalled(actorName)
                .whoCan(BrowseTheWebWithPlaywright.usingTheDefaultConfiguration());
    }

    @When("navega a la pagina principal")
    public void navegaALaPaginaPrincipal() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(NavigateToHomePage.ofSelenium());
    }

}