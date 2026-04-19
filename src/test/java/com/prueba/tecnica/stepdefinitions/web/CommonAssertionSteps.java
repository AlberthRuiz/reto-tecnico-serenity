package com.prueba.tecnica.stepdefinitions.web;

import com.prueba.tecnica.questions.web.CurrentUrl;
import com.prueba.tecnica.questions.web.PageTitle;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

public class CommonAssertionSteps {

    @Then("el título de la pagina debe ser {string}")
    public void elTituloDeLaPaginaDebeSer(String expectedTitle) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(Ensure.that(PageTitle.current()).isEqualTo(expectedTitle));
    }

    @And("el titulo de la pagina debe contener {string}")
    public void elTituloDeLaPaginaDebeContener(String titleFragment) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(Ensure.that(PageTitle.current()).contains(titleFragment));
    }

    @And("la URL actual debe contener {string}")
    public void laURLActualDebeContener(String expectedURL) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(Ensure.that(CurrentUrl.displayed()).contains(expectedURL));
    }
}
