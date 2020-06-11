package com.bracongo.callcenter.entities.dto;

import java.io.Serializable;

/**
 *
 * @author Valmy Roi Kenfack <vr.kenfack at bracongo.cd>
 */
public class LoginResponse implements Serializable{
    
    private String username;
    
    private String accessToken;
    
    private String role;
    
    private String tokenType = "Bearer";
    
    private boolean status;

    public LoginResponse(String username, String accessToken) {
        this.username = username;
        this.accessToken = accessToken;
    }

    public LoginResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }  

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    
}
