package br.com.trello.services;

import br.com.core.report.ExtentReports;
import br.com.trello.interfaces.Headers;
import br.com.trello.model.Credentials;
import br.com.trello.model.Environment;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class CardServices implements Headers {

    private Credentials credentials;
    private Environment environment;
    private Response response;

    public CardServices() {
        credentials = new Credentials();
        environment = new Environment();
    }

    /**
     * Adiciona cartão no quadro kanban
     * @param idList id da coluna do kanban
     * @param name nome do cartão
     * @return id do cartão
     */
    public String createCard(String idList, String name){
        String url = environment.getUrl() + "/1/cards?idList="+idList+"&name="+name+"&keepFromSource=all&key="
                + credentials.getTrelloKey() + "&token=" + credentials.getTrelloToken();
        ExtentReports.appendToReport("Request: " + url);
        RequestSpecification httpRequest = given();
        httpRequest.header("content-type",HEADER_JSON);
        httpRequest.header("content-type",HEADER_CHARSET);
        response = httpRequest.post(url);
        ExtentReports.appendToReport("Response: " +response.body().prettyPrint());
        Assert.assertEquals(response.statusCode(),200);
        return response.getBody().jsonPath().get("id");
    }

    /**
     * Atualiza o nome do cartão no quadro kanban
     * @param idCard id da cartão do kanban
     * @param name novo nome para o cartão
     */
    public void updateCardName(String idCard, String name){
        String url = environment.getUrl() + "/1/cards/"+idCard+"?name="+name+"&key=" +
                credentials.getTrelloKey() + "&token=" + credentials.getTrelloToken();
        ExtentReports.appendToReport("Request: " + url);
        RequestSpecification httpRequest = given();
        httpRequest.header("content-type",HEADER_JSON);
        httpRequest.header("content-type",HEADER_CHARSET);
        response = httpRequest.put(url);
        ExtentReports.appendToReport("Response: " +response.body().prettyPrint());
        Assert.assertEquals(response.statusCode(),200);
    }

    /**
     * Move o cartão entre as colunas no quadro kanban
     * @param idCard id da cartão do kanban
     * @param idList id da coluna do kanban
     */
    public void moveCard(String idCard, String idList){
        String url = environment.getUrl() + "/1/cards/"+idCard+"/idList?value="+idList+"&key="
                + credentials.getTrelloKey() + "&token=" + credentials.getTrelloToken();
        ExtentReports.appendToReport("Request: " + url);
        RequestSpecification httpRequest = given();
        httpRequest.header("content-type",HEADER_JSON);
        httpRequest.header("content-type",HEADER_CHARSET);
        response = httpRequest.put(url);
        ExtentReports.appendToReport("Response: " +response.body().prettyPrint());
        Assert.assertEquals(response.statusCode(),200);
    }

}
