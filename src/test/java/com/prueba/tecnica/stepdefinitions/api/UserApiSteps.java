package com.prueba.tecnica.stepdefinitions.api;

import com.prueba.tecnica.abilities.CallReqresApi;
import com.prueba.tecnica.tasks.api.CreateUser;
import com.prueba.tecnica.tasks.api.ListUsers;
import com.prueba.tecnica.tasks.api.UpdateUser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.OnStage;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UserApiSteps {
    @Given("el cliente API tiene acceso a Reqres")
    public void elClienteApiTieneAcceso() {
        OnStage.theActorCalled("Cliente API")
                .whoCan(CallReqresApi.asApiClient());
    }

    @When("solicita el listado de usuarios en la pagina {int}")
    public void solicitaElListadoDeUsuariosEnLaPagina(int page) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ListUsers.onPage(page)
        );
    }

    @When("crea un usuario con el nombre {string} y trabajo {string}")
    public void creaUsuarioConElNombreYTrabajo(String name, String job) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                CreateUser.withNameAndJob(name, job)
        );
    }

    @When("actualiza el usuario con id {int} cambiando el nombre a {string} y el trabajo a {string}")
    public void actualizaUsuarioConId2CambiarNombreA(int id, String name, String job) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                UpdateUser.withIdAndData(id, name, job)
        );
    }

    @Then("la respuesta tiene codigo {int}")
    public void laRespuestaTieneCodigo(int code) {
        assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(code);
    }

    @And("la respuesta contiene una lista con al menos un usuario")
    public void contieneListaConAlMenosUnUsuario() {
        Response response = SerenityRest.lastResponse();
        List<?> data = response.jsonPath().getList("data");
        assertThat(data).isNotNull().isNotEmpty();
    }

    @And("la respuesta contiene los campos de paginacion:")
    public void contieneCamposPaginacion(List<String> campos) {
        Response response = SerenityRest.lastResponse();
        for (String campo : campos) {
            Object valor = response.jsonPath().get(campo);
            assertThat(valor)
                    .as("Campo '%s' en respuesta", campo)
                    .isNotNull();
        }
    }

    @And("todos los emails terminan en {string}")
    public void todosLosEmailsTerminanEn(String suffix) {
        List<String> emails = SerenityRest.lastResponse().jsonPath().getList("data.email", String.class);
        assertThat(emails).allMatch(email -> email.endsWith(suffix));
    }

    @And("todos los avatares son URLs con {string}")
    public void todosLosAvataresContienen(String fragment) {
        List<String> avatars = SerenityRest.lastResponse().jsonPath().getList("data.avatar", String.class);
        assertThat(avatars).allMatch(url -> url.contains(fragment));
    }

    @And("cada usuario contiene los campos:")
    public void cadaUsuarioContieneCampos(List<String> campos) {
        Response response = SerenityRest.lastResponse();
        List<Map<String, Object>> usuarios = response.jsonPath().getList("data");
        for (Map<String, Object> usuario : usuarios) {
            assertThat(usuario.keySet()).containsAll(campos);
        }
    }

    @And("la respuesta contiene el nombre {string} y el trabajo {string}")
    public void contieneNombreYTrabajo(String name, String job) {
        Response response = SerenityRest.lastResponse();
        assertThat(response.jsonPath().getString("name")).isEqualTo(name);
        assertThat(response.jsonPath().getString("job")).isEqualTo(job);
    }

    @And("la respuesta contiene un id generado")
    public void contieneIdGenerado() {
        String id = SerenityRest.lastResponse().jsonPath().getString("id");
        assertThat(id).isNotBlank();
    }

    @And("la respuesta contiene un updatedAt")
    public void contieneUpdatedAt() {
        Response response = SerenityRest.lastResponse();
        String updatedAt = response.jsonPath().getString("updatedAt");
        assertThat(updatedAt).isNotBlank();
        assertThat(Instant.parse(updatedAt)).isNotNull();
    }

}
