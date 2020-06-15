package com.bracongo.callcenter.entities.dto;

import com.bracongo.callcenter.entities.CommandeItem;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vr.kenfack
 */
public class CommandeDto implements Serializable{
    
    private String client;
    
    private List<CommandeItem> items;
    
    private String username;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public List<CommandeItem> getItems() {
        return items;
    }

    public void setItems(List<CommandeItem> items) {
        this.items = items;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "CommandeDto{" + "client=" + client + ", items=" + items + ", username=" + username + '}';
    }
    
    
    
    
}
