package com.prueba.tecnica.questions.web;

import com.microsoft.playwright.Page;
import com.prueba.tecnica.ui.SearchPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;

import java.util.List;

public class SearchResults implements Question<List<String>> {

    public static SearchResults titles() {
        return new SearchResults();
    }
    @Override
    public List<String> answeredBy(Actor actor) {
        Page page = BrowseTheWebWithPlaywright.as(actor).getCurrentPage();
        return page.locator(SearchPage.SEARCH_HIT_TITLE).allTextContents();
    }
}