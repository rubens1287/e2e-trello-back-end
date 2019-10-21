package gherkin.stepdefinition;

import br.com.trello.services.BoardServices;
import br.com.trello.services.CardServices;
import br.com.trello.services.ListServices;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrelloSteps {

    private BoardServices boardServices = new BoardServices();
    private ListServices listServices = new ListServices();
    private CardServices cardServices = new CardServices();
    private String idBoard;
    private String idCard;
    private ArrayList<HashMap<String,String>> colunas;

    @Dado("eu crio um quadro")
    public void euCrioUmQuadro(DataTable dataTable) {
        List<Map<String,String>> table = dataTable.asMaps();

        for (Map<String,String> row : table){
            for(String campo : row.keySet()){
                String resposta = row.get(campo);
                switch (campo.toUpperCase()){
                    case "QUADRO":
                        idBoard = boardServices.createBoard(resposta);
                        break;
                    default:
                        Assert.fail("Coluna não encontrada!");
                }
            }
        }
    }

    @E("crio as colunas")
    public void crioAsColunas(DataTable dataTable) {

        String[] colunasExistentes;
        List<Map<String,String>> table = dataTable.asMaps();

        for (Map<String,String> row : table){
            for(String campo : row.keySet()){
                String resposta = row.get(campo);
                switch (campo.toUpperCase()){
                    case "COLUNAS":
                        colunas = listServices.getLists(idBoard);
                        colunasExistentes = resposta.split(",");
                        for (int i = 0; i < colunasExistentes.length; i++) {
                            if(i <= 2){
                                listServices.updateListName(colunas.get(i).get("id"),colunasExistentes[i]);    
                            }else {
                                listServices.createList(idBoard,colunasExistentes[i]);
                            }
                        }
                        colunas = listServices.getLists(idBoard);
                        break;
                    default:
                        Assert.fail("Coluna não encontrada!");
                }
            }
        }
    }

    @E("crio um cartao")
    public void crioUmCartao(DataTable dataTable) {
        List<Map<String,String>> table = dataTable.asMaps();

        for (Map<String,String> row : table){
            for(String campo : row.keySet()){
                String resposta = row.get(campo);
                switch (campo.toUpperCase()){
                    case "CARD":
                        idCard = cardServices.createCard(colunas.get(0).get("id"),resposta);
                        break;
                    default:
                        Assert.fail("Coluna não encontrada!");
                }
            }
        }
    }

    @E("altero o nome do cartao")
    public void alterarONomeDoCartao(DataTable dataTable) {
        List<Map<String,String>> table = dataTable.asMaps();

        for (Map<String,String> row : table){
            for(String campo : row.keySet()){
                String resposta = row.get(campo);
                switch (campo.toUpperCase()){
                    case "CARD":
                        cardServices.updateCardName(idCard,resposta);
                        break;
                    default:
                        Assert.fail("Coluna não encontrada!");
                }
            }
        }
    }

    @E("movo o cartao para {string}")
    public void moverOCartaoPara(String listName) {
        for (int i = 0; i < colunas.size() ; i++) {
            if(listName.toUpperCase().contains(colunas.get(i).get("name").toUpperCase())){
                cardServices.moveCard(idCard,colunas.get(i).get("id"));
            }
        }
    }

    @Entao("eu excluo o quadro")
    public void euExcluoOQuadro() {
        boardServices.deleteBoard(idBoard);
    }
}
