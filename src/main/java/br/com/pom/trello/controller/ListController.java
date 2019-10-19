package br.com.pom.trello.controller;

import br.com.pom.trello.interfaces.Credentials;
import br.com.pom.trello.interfaces.Environment;
import br.com.pom.trello.model.List;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ListController implements Environment, Credentials {

    public List getColunas(String idBoard){
        List list;
        RequestSpecification httpRequest = given();
        httpRequest.header("content-type","application/json; charset=utf-8");
        Response response = httpRequest.get(URL_BASE + "/1/boards/"+idBoard+"/?lists=all&list_fields=name&key=" + KEY + "&token=" + TOKEN);
        list = response.as(List.class, ObjectMapperType.GSON);
        return response.getBody().jsonPath().get("id");
    }

    @Test
    public void test(){
        getColunas("2klQ5x0g");
    }

}
