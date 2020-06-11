package com.bracongo.callcenter.entities.dto;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class PlainteDto implements Serializable{
    
    private String username;
    
    private String description;
    
    private String typePlainte;
    
    private String client;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypePlainte() {
        return typePlainte;
    }

    public void setTypePlainte(String typePlainte) {
        this.typePlainte = typePlainte;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
    
    
    
}
