package com.prueba.tecnica.stepdefinitions.web;

import com.microsoft.playwright.Page;
import com.prueba.tecnica.questions.web.ElementVisibility;
import com.prueba.tecnica.tasks.web.NavigateToDocumentation;
import com.prueba.tecnica.tasks.web.NavigateToHomePage;
import com.prueba.tecnica.ui.DocumentationPage;
import com.prueba.tecnica.utils.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;
import net.serenitybdd.screenplay.playwright.interactions.Open;

import static org.assertj.core.api.Assertions.assertThat;

public class DocumentationSteps {
    @Given("{word} se encuentra en la pagina principal de Selenium")
    public void seEncuentraEnLaPaginaPrincipal(String actorName) {
        OnStage.theActorCalled(actorName)
                .whoCan(BrowseTheWebWithPlaywright.usingTheDefaultConfiguration())
                .attemptsTo(NavigateToHomePage.ofSelenium());
    }

    @Given("{word} quiere acceder a una pagina de documentacion especifica")
    public void quiereAccederDirectamente(String actorName) {
        OnStage.theActorCalled(actorName)
                .whoCan(BrowseTheWebWithPlaywright.usingTheDefaultConfiguration());
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

    @When("navega directamente a la ruta {string}")
    public void navegaDirectamente(String ruta) {
        String urlCompleta = Config.webBaseUrl() + ruta;
        OnStage.theActorInTheSpotlight().attemptsTo(Open.url(urlCompleta));
    }

    @And("el encabezado principal muestra {string}")
    public void elEncabezadoPrincipalMuestra(String texto) {
        Page page = BrowseTheWebWithPlaywright.as(OnStage.theActorInTheSpotlight()).getCurrentPage();
        String h1 = page.locator("h1").first().textContent();
        assertThat(h1).contains(texto);
    }
}