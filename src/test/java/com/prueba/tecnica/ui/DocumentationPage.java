package com.prueba.tecnica.ui;

import com.prueba.tecnica.utils.Config;

public class DocumentationPage {

    public static String url(){
        return Config.webBaseUrl() + "/documentation/";
    }

    public static final String WEBDRIVER_LINK = "a[href='/documentation/webdriver/']";
}
