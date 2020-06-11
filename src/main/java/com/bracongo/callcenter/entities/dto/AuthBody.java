package com.bracongo.callcenter.entities.dto;

import java.io.Serializable;

/**
 *
 * @author Valmy Roi Kenfack <vr.kenfack at bracongo.cd>
 */
public class AuthBody implements Serializable{
    
    private String username;
    
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
