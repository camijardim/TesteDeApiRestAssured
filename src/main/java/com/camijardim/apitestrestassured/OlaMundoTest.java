package com.camijardim.apitestrestassured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class OlaMundoTest {

    @Test
    public void testeOlaMundo() {
        Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");
        Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
        Assert.assertTrue(response.statusCode() == 200);
        Assert.assertTrue("O status code deveria ser 200",response.statusCode() == 200);
        Assert.assertEquals(201,response.statusCode());

        ValidatableResponse validacao = response.then();
        validacao.statusCode(200);
    }

    @Test
    public void devoConhecerOutrasFormasRestAssured() {
        // para fazer o import static = alt + enter
        // antes de por * no static import - Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");
        Response response = request(Method.GET, "https://restapi.wcaquino.me/ola");
        ValidatableResponse validacao = response.then();
        validacao.statusCode(200);

        // forma + simplificada
        // import static = alt + enter
        //import static io.restassured.RestAssured.get; se colar * no lugar do get, consigo mexer onde tiver RestAssured
        get("https://restapi.wcaquino.me/ola").then().statusCode(200);

        // agora usando o given, when e then
        // antes da linha poderia ter o RestAssured.given().... mas como coloquei * no import static n preciso mais...
        given()
        .when()
            .get("https://restapi.wcaquino.me/ola")
        .then()
            .statusCode(200);
    }

    @Test
    public void devoConhecerMatcherHamcrest() {
        assertThat("Maria", Matchers.is("Maria"));
        assertThat(128, Matchers.is(128));
        assertThat(128, Matchers.isA(Integer.class));
        assertThat(128d, Matchers.isA(Double.class));
        assertThat(128d, Matchers.greaterThan(120d));
        assertThat(128d, Matchers.lessThan(130d));

        List<Integer> impares = Arrays.asList(1,3,5,7,9);
        //Assert.assertThat(impares, Matchers.<Integer>hasSize(5)); - SEM IMPORT STATIC
        assertThat(impares, hasSize(5));
        assertThat(impares, contains(1,3,5,7,9)); // tem q ser na msm ordem
        assertThat(impares, containsInAnyOrder(1,7,9,3,5)); //conferir se tem os elementos msm passando fora da ordem
        assertThat(impares, hasItem(1)); //se contém APENAS UM elemento mesmo n passando todos os elementos presentes
        assertThat(impares, hasItems(1, 5));

        assertThat("Maria", is(not("João")));
        assertThat("Maria", not("João"));
        assertThat("Joaquina", anyOf(is("Maria"), is("Joaquina")));
        assertThat("Joaquina", allOf(startsWith("Joa"), endsWith("ina"), containsString("qui")));
    }

    @Test
    public void devoValidarBody() {
        given()
                .when()
                    .get("https://restapi.wcaquino.me/ola")
                .then()
                .statusCode(200)
                .body(is("Ola Mundo!"))
                .body(containsString("Mundo"))
                .body(is(not(nullValue())));


    }

}
