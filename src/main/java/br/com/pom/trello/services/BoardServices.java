package br.com.pom.trello.services;

import br.com.core.report.ExtentReports;
import br.com.pom.trello.interfaces.Headers;
import br.com.pom.trello.model.Credentials;
import br.com.pom.trello.model.Environment;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class BoardServices implements Headers {

    private Credentials credentials;
    private Environment environment;
    private Response response;

    public BoardServices() {
        credentials = new Credentials();
        environment = new Environment();
    }

    /**
     *  Cria um quadro kanban
     * @param name nome do quadro
     * @return id do quadro
     */
    public String createBoard(String name){
        String url = environment.getUrl() + "/1/boards/?name=" + name + "&key="
                + credentials.getTrelloKey() + "&token=" + credentials.getTrelloToken();
        RequestSpecification httpRequest = given();
        httpRequest.header("content-type",HEADER_JSON);
        httpRequest.header("content-type",HEADER_CHARSET);
        ExtentReports.appendToReport("Request: " + url);
        response = httpRequest.post(url);
        Assert.assertEquals(response.getStatusCode(),200);
        ExtentReports.appendToReport("Response: " +response.body().jsonPath().prettyPrint());
        return response.getBody().jsonPath().get("id");
    }

    /**
     * Deleta quadro kanban
     * @param idBoard id do quadro kanben
     */
    public void deleteBoard(String idBoard){
        String url = environment.getUrl() + "/1/boards/"+idBoard+"&key="
                + credentials.getTrelloKey() + "&token=" + credentials.getTrelloToken();
        RequestSpecification httpRequest = given();
        httpRequest.header("content-type",HEADER_JSON);
        httpRequest.header("content-type",HEADER_CHARSET);
        response = httpRequest.delete(url);
        Assert.assertEquals(response.getStatusCode(),200);
        ExtentReports.appendToReport("Response: " +response.body().jsonPath().prettyPrint());
    }
}
