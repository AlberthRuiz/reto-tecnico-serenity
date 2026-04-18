package com.prueba.tecnica.utils;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class Config {
    private static final EnvironmentVariables ENV =
            SystemEnvironmentVariables.createEnvironmentVariables();

    public static String webBaseUrl() {
        return EnvironmentSpecificConfiguration.from(ENV).getProperty("webdriver.base.url");
    }

    public static String apiBaseUrl() {
        return EnvironmentSpecificConfiguration.from(ENV).getProperty("restapi.baseurl");
    }

    public static String reqresApiKey() {
        String envKey = System.getenv("REQRES_API_KEY");
        if (envKey != null && !envKey.isBlank()) {
            return envKey;
        }
        return EnvironmentSpecificConfiguration.from(ENV).getProperty("reqres.apikey");

    }

}
