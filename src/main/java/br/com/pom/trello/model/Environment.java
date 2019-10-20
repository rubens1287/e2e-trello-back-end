package br.com.pom.trello.model;

import br.com.core.properties.PropertiesManager;
import br.com.pom.trello.interfaces.Property;

public class Environment implements Property {

    private String url;

    public Environment() {
        PropertiesManager propertiesManager = new PropertiesManager(PATH_PROPERTY);
        setUrl(propertiesManager.getProps().getProperty("TRELLO_URL"));
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
