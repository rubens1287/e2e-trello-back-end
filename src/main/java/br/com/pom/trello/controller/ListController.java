package br.com.pom.trello.controller;

import br.com.pom.trello.interfaces.Credentials;
import br.com.pom.trello.interfaces.Environment;
import br.com.pom.trello.model.ListModel;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ListController implements Environment, Credentials {

    public ArrayList<HashMap<String,String>> getColunas(String idBoard){
        HashMap<String,String> hash = new HashMap<>();
        RequestSpecification httpRequest = given();
        httpRequest.header("content-type","application/json; charset=utf-8");
        Response response = httpRequest.get(URL_BASE + "/1/boards/"+idBoard+"/?lists=all&list_fields=name&key=" + KEY + "&token=" + TOKEN);
        return response.getBody().jsonPath().get("lists");
    }

    @Test
    public void test(){
        ArrayList<HashMap<String,String>> teste = getColunas("2klQ5x0g");
    }

}
