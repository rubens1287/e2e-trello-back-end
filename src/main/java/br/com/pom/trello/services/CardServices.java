package br.com.pom.trello.services;

import br.com.pom.trello.interfaces.Headers;
import br.com.pom.trello.model.Credentials;
import br.com.pom.trello.model.Environment;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class CardServices implements Headers {

    private Credentials credentials;
    private Environment environment;

    public CardServices() {
        credentials = new Credentials();
        environment = new Environment();
    }

    /**
     * Adiciona cartão no quadro kanban
     * @param idList id da coluna do kanban
     * @param name nome do cartão
     * @return objeto com o retorno da requisição
     */
    public Response createCard(String idList, String name){
        RequestSpecification httpRequest = given();
        httpRequest.header("content-type",HEADER_JSON);
        httpRequest.header("content-type",HEADER_CHARSET);
        return httpRequest.post(environment.getUrl() + "/1/cards?idList="+idList+"&name="+name+"&keepFromSource=all&key="
                + credentials.getTrelloKey() + "&token=" + credentials.getTrelloToken());
    }

    /**
     * Atualiza o nome do cartão no quadro kanban
     * @param idCard id da cartão do kanban
     * @param name novo nome para o cartão
     * @return objeto com o retorno da requisição
     */
    public Response updateCardName(String idCard, String name){
        RequestSpecification httpRequest = given();
        httpRequest.header("content-type",HEADER_JSON);
        httpRequest.header("content-type",HEADER_CHARSET);
        return httpRequest.put(environment.getUrl() + "/1/cards/"+idCard+"?name="+name+"&key=" +
                credentials.getTrelloKey() + "&token=" + credentials.getTrelloToken());
    }

    /**
     * Move o cartão entre as colunas no quadro kanban
     * @param idCard id da cartão do kanban
     * @param idList id da coluna do kanban
     * @return objeto com o retorno da requisição
     */
    public Response moveCard(String idCard, String idList){
        RequestSpecification httpRequest = given();
        httpRequest.header("content-type",HEADER_JSON);
        httpRequest.header("content-type",HEADER_CHARSET);
        return httpRequest.put(environment.getUrl() + "/1/cards/"+idCard+"/idList?value="+idList+"&key="
                + credentials.getTrelloKey() + "&token=" + credentials.getTrelloToken());
    }

}
