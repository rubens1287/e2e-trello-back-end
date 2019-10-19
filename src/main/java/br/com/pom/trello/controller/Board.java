package br.com.pom.trello.controller;

import br.com.pom.trello.interfaces.Credentials;
import br.com.pom.trello.interfaces.Environment;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Board implements Environment, Credentials {

    private Response response;

    public String criarQuadro(String nome){

        RequestSpecification httpRequest = given();
        response = httpRequest.post("");

        return "";
    }



}
