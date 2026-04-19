package com.prueba.tecnica.stepdefinitions.web;

import com.prueba.tecnica.questions.web.ElementVisibility;
import com.prueba.tecnica.tasks.web.NavigateToDocumentation;
import com.prueba.tecnica.tasks.web.NavigateToHomePage;
import com.prueba.tecnica.ui.DocumentationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;

public class DocumentationSteps {
    @Given("{word} se encuentra en la pagina principal de Selenium")
    public void seEncuentraEnLaPaginaPrincipal(String actorName) {
        OnStage.theActorCalled(actorName)
                .whoCan(BrowseTheWebWithPlaywright.usingTheDefaultConfiguration())
                .attemptsTo(NavigateToHomePage.ofSelenium());
    }

    @When("hace click en el enlace {string}")
    public void haceClicEnElEnlace(String linkName) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(NavigateToDocumentation.fromHomePage());
    }

    @And("la pagina de Documentation debe mostrarse correctamente")
    public void laPaginaDeDocumentacionDebeMostrarseCorrectamente() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(Ensure.that(ElementVisibility.of(DocumentationPage.WEBDRIVER_LINK))
                        .isTrue());
    }
}