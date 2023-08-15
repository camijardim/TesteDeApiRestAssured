package com.camijardim.apitestrestassured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

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

}
