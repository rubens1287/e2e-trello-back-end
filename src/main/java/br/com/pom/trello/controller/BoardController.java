package br.com.pom.trello.controller;

import br.com.pom.trello.interfaces.Credentials;
import br.com.pom.trello.interfaces.Environment;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BoardController implements Environment, Credentials {

    public String criarQuadro(String nome){
        RequestSpecification httpRequest = given();
        httpRequest.header("content-type","application/json; charset=utf-8");
        Response response = httpRequest.post(URL_BASE + "/1/boards/?name=" + nome + "&key=" + KEY + "&token=" + TOKEN);
        return response.getBody().jsonPath().get("id");
    }

}
