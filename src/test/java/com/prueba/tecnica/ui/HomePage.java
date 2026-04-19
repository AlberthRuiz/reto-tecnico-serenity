package com.prueba.tecnica.ui;

import com.prueba.tecnica.utils.Config;

public class HomePage {
    public static String url(){
        return Config.webBaseUrl();
    }
    public static final String DOCUMENTATION_LINK = "a[href='/documentation']";
}
