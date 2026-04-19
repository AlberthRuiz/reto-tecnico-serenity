package com.prueba.tecnica.stepdefinitions.web;

import com.microsoft.playwright.Page;
import com.prueba.tecnica.questions.web.SearchResults;
import com.prueba.tecnica.tasks.web.OpenDocumentationPage;
import com.prueba.tecnica.tasks.web.SearchFor;
import com.prueba.tecnica.ui.SearchPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchSteps {

    @Given("{word} se encuentra en la pagina de Documentacion de Selenium")
    public void seEncuentraEnPaginaDocumentacion(String actorName) {
        OnStage.theActorCalled(actorName)
                .whoCan(BrowseTheWebWithPlaywright.usingTheDefaultConfiguration())
                .attemptsTo(OpenDocumentationPage.directly());
    }

    @When("busca el termino {string}")
    public void buscaElTermino(String term) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(SearchFor.theTerm(term));
    }

    @Then("los resultados de busqueda contienen {string}")
    public void losResultadosDeBusquedaContienen(String term) {
        Actor actor = OnStage.theActorInTheSpotlight();
        List<String> titles = actor.asksFor(SearchResults.titles());
        assertThat(titles)
                .as("Resultados de busqueda para '%s'", term)
                .isNotEmpty()
                .anyMatch(title -> title.toLowerCase().contains(term.toLowerCase()));
    }

    @Then("aparece el mensaje {string} en el buscador")
    public void apareceMensajeEnBuscador(String textoEsperado) {
        com.microsoft.playwright.Page page = net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright
                .as(OnStage.theActorInTheSpotlight()).getCurrentPage();
        String texto = page.locator(com.prueba.tecnica.ui.SearchPage.NO_RESULTS_MESSAGE).textContent();
        assertThat(texto).contains(textoEsperado);
    }
}