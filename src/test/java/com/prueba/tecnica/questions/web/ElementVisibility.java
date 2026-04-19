package com.prueba.tecnica.questions.web;

import com.microsoft.playwright.Page;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;

public class ElementVisibility implements Question<Boolean> {
    private final String selector;
    private ElementVisibility(String selector) {
        this.selector = selector;
    }

    public static ElementVisibility of(String selector) {
        return new ElementVisibility(selector);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        Page page = BrowseTheWebWithPlaywright.as(actor).getCurrentPage();
        return page.locator(selector).first().isVisible();
    }
}