package br.com.trello.model;

import br.com.core.properties.PropertiesManager;
import br.com.trello.interfaces.Property;

public class Credentials implements Property {

    private String trelloKey;
    private String trelloToken;

    public Credentials() {
        PropertiesManager propertiesManager = new PropertiesManager(PATH_PROPERTY);
        setTrelloKey(propertiesManager.getProps().getProperty("TRELLO_KEY"));
        setTrelloToken(propertiesManager.getProps().getProperty("TRELLO_TOKEN"));
    }

    public String getTrelloKey() {
        return trelloKey;
    }

    public void setTrelloKey(String trelloKey) {
        this.trelloKey = trelloKey;
    }

    public String getTrelloToken() {
        return trelloToken;
    }

    public void setTrelloToken(String trelloToken) {
        this.trelloToken = trelloToken;
    }
}
