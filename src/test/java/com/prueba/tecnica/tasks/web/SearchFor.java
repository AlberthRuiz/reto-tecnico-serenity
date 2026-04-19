package com.prueba.tecnica.tasks.web;
import com.microsoft.playwright.Page;
import com.prueba.tecnica.ui.SearchPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;
import net.serenitybdd.screenplay.playwright.interactions.Click;
import net.serenitybdd.screenplay.playwright.interactions.Enter;

public class SearchFor implements Task {

    private final String term;

    public SearchFor(String term) {
        this.term = term;
    }

    public static SearchFor theTerm(String term) {
        return new SearchFor(term);
    }

    @Override
    @Step("{0} busca el término '#term'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(SearchPage.SEARCH_BUTTON),
                Enter.theValue(term).into(SearchPage.SEARCH_INPUT)
        );
        Page page = BrowseTheWebWithPlaywright.as(actor).getCurrentPage();
        page.locator(SearchPage.SEARCH_HIT_TITLE).first().waitFor();
    }
}