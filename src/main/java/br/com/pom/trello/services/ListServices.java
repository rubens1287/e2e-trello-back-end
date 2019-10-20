package br.com.pom.trello.services;

import br.com.core.report.ExtentReports;
import br.com.pom.trello.interfaces.Headers;
import br.com.pom.trello.model.Credentials;
import br.com.pom.trello.model.Environment;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ListServices implements Headers {

    private Credentials credentials;
    private Environment environment;
    private Response response;

    public ListServices() {
        credentials = new Credentials();
        environment = new Environment();
    }

    /**
     * Retorna a lista de ids e nomes das colunas atuais de quadro
     * @param idBoard id do board
     * @return objeto hashMap com a lista de colunas
     */
    public ArrayList<HashMap<String,String>> getLists(String idBoard){
        String url = environment.getUrl() + "/1/boards/"+idBoard+"/?lists=all&list_fields=name&key="
                + credentials.getTrelloKey() + "&token=" + credentials.getTrelloToken();
        ExtentReports.appendToReport("Request: " + url);
        RequestSpecification httpRequest = given();
        httpRequest.header("content-type",HEADER_JSON);
        httpRequest.header("content-type",HEADER_CHARSET);
        response = httpRequest.get(url);
        Assert.assertEquals(response.statusCode(),200);
        ExtentReports.appendToReport("Response: " +response.body().prettyPrint());
        return response.getBody().jsonPath().get("lists");
    }

    /**
     * Atualiza o nome de uma coluna no quadro
     * @param idList id da coluna
     * @param name nome que será utilizado para atualizar o nome da coluna atual
     */
    public void updateListName(String idList, String name){
        String url = environment.getUrl() + "/1/lists/"+idList+"/?name="+name+"&key="
                + credentials.getTrelloKey() + "&token=" + credentials.getTrelloToken();
        ExtentReports.appendToReport("Request: " + url);
        RequestSpecification httpRequest = given();
        httpRequest.header("content-type",HEADER_JSON);
        httpRequest.header("content-type",HEADER_CHARSET);
        response = httpRequest.put(url);
        Assert.assertEquals(response.statusCode(),200);
        ExtentReports.appendToReport("Response: "+response.body().prettyPrint());
    }

    /**
     * Atualiza o nome de uma coluna no quadro
     * @param idBoard id do quadro kanban
     * @param name nome que será utilizado para atualizar o nome da coluna atual
     * @return id da coluna
     */
    public String createList(String idBoard, String name){
        String url = environment.getUrl() + "/1/lists?name="+name+"&idBoard="+idBoard+"&key="
                + credentials.getTrelloKey() + "&token=" + credentials.getTrelloToken();
        ExtentReports.appendToReport("Request: " + url);
        RequestSpecification httpRequest = given();
        httpRequest.header("content-type",HEADER_JSON);
        httpRequest.header("content-type",HEADER_CHARSET);
        response = httpRequest.put(url);
        Assert.assertEquals(response.statusCode(),200);
        ExtentReports.appendToReport("Response: "+response.body().prettyPrint());
        return response.getBody().jsonPath().get("id");
    }
}
