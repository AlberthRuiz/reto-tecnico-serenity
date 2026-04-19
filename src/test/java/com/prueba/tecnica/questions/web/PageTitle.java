package com.prueba.tecnica.questions.web;

import com.microsoft.playwright.Page;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;

public class PageTitle implements Question<String> {
    public static PageTitle current() {
        return new PageTitle();
    }
    @Override
    public String answeredBy(Actor actor) {
        Page page = BrowseTheWebWithPlaywright.as(actor).getCurrentPage();
        return page.title();
    }
}
