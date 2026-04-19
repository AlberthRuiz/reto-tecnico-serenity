package com.prueba.tecnica.stepdefinitions.api;

import com.prueba.tecnica.abilities.CallReqresApi;
import com.prueba.tecnica.questions.api.ApiResponse;
import com.prueba.tecnica.tasks.api.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UserApiSteps {

    private String primerId;

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

    @When("crea otro usuario con el nombre {string} y trabajo {string} otra vez")
    public void creaOtroUsuarioConElNombreYTrabajo(String name, String job) {
        Actor actor = OnStage.theActorInTheSpotlight();
        primerId = actor.asksFor(ApiResponse.field("id"));
        actor.attemptsTo(CreateUser.withNameAndJob(name, job));
    }

    @Then("la respuesta tiene codigo {int}")
    public void laRespuestaTieneCodigo(int code) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(Ensure.that(ApiResponse.statusCode()).isEqualTo(code));
    }

    @Then("los dos ids generados son distintos")
    public void losIdsSonDistintos() {
        String segundoId = OnStage.theActorInTheSpotlight().asksFor(ApiResponse.field("id"));
        assertThat(segundoId)
                .as("El segundo POST debe generar un id distinto al primero")
                .isNotEqualTo(primerId)
                .isNotBlank();
    }

    @And("la respuesta contiene una lista con al menos un usuario")
    public void contieneListaConAlMenosUnUsuario() {
        List<Map<String, Object>> data = OnStage.theActorInTheSpotlight()
                .asksFor(ApiResponse.objectList("data"));
        assertThat(data).isNotNull().isNotEmpty();
    }

    @And("la respuesta contiene los campos de paginacion:")
    public void contieneCamposPaginacion(List<String> campos) {
        Actor actor = OnStage.theActorInTheSpotlight();
        for (String campo : campos) {
            String valor = actor.asksFor(ApiResponse.field(campo));
            assertThat(valor)
                    .as("Campo '%s' en respuesta", campo)
                    .isNotNull();
        }
    }

    @And("todos los emails terminan en {string}")
    public void todosLosEmailsTerminanEn(String suffix) {
        List<String> emails = OnStage.theActorInTheSpotlight()
                .asksFor(ApiResponse.stringList("data.email"));
        assertThat(emails).allMatch(email -> email.endsWith(suffix));
    }

    @And("todos los avatares son URLs con {string}")
    public void todosLosAvataresContienen(String fragment) {
        List<String> avatars = OnStage.theActorInTheSpotlight()
                .asksFor(ApiResponse.stringList("data.avatar"));
        assertThat(avatars).allMatch(url -> url.contains(fragment));
    }

    @And("cada usuario contiene los campos:")
    public void cadaUsuarioContieneCampos(List<String> campos) {
        List<Map<String, Object>> usuarios = OnStage.theActorInTheSpotlight()
                .asksFor(ApiResponse.objectList("data"));
        for (Map<String, Object> usuario : usuarios) {
            assertThat(usuario.keySet()).containsAll(campos);
        }
    }

    @And("la respuesta contiene el nombre {string} y el trabajo {string}")
    public void contieneNombreYTrabajo(String name, String job) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Ensure.that(ApiResponse.field("name")).isEqualTo(name),
                Ensure.that(ApiResponse.field("job")).isEqualTo(job)
        );
    }

    @And("la respuesta contiene un id generado")
    public void contieneIdGenerado() {
        String id = OnStage.theActorInTheSpotlight().asksFor(ApiResponse.field("id"));
        assertThat(id).isNotBlank();
    }

    @And("la respuesta contiene un updatedAt")
    public void contieneUpdatedAt() {
        String updatedAt = OnStage.theActorInTheSpotlight().asksFor(ApiResponse.field("updatedAt"));
        assertThat(updatedAt).isNotBlank();
        assertThat(Instant.parse(updatedAt)).isNotNull();
    }

    @And("el createdAt esta dentro de los ultimos 60 segundos")
    public void createdAtEsReciente() {
        String createdAtStr = OnStage.theActorInTheSpotlight().asksFor(ApiResponse.field("createdAt"));
        Instant createdAt = Instant.parse(createdAtStr);
        Instant hace60s = Instant.now().minusSeconds(60);
        assertThat(createdAt).isAfter(hace60s);
    }

    @And("todas las URLs de avatar responden 200")
    public void avataresResponden200() {
        Actor actor = OnStage.theActorInTheSpotlight();
        List<String> avatars = actor.asksFor(ApiResponse.stringList("data.avatar"));
        for (String url : avatars) {
            int code = actor.asksFor(ApiResponse.headStatusCode(url));
            assertThat(code).as("Avatar %s", url).isEqualTo(200);
        }
    }

    @And("cada usuario tiene su avatar con su ID en la URL")
    public void avatarCorrespondeConIdEnLaURL() {
        List<Map<String, Object>> users = OnStage.theActorInTheSpotlight()
                .asksFor(ApiResponse.objectList("data"));
        for (Map<String, Object> user : users) {
            Integer id = (Integer) user.get("id");
            String avatar = (String) user.get("avatar");
            assertThat(avatar)
                    .as("Avatar usuario ID: %d", id)
                    .contains("/" + id + "-image");
        }
    }
}