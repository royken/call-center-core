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
    
    private int quantite;
    
    private int prixTotal;
    
    private String sup;
    
    private String telSup;
    
    private String mailSup;
    
    private String merch;
    
    private String telMerch;
    
    private String mailMerch;
    
    private String raisonSociale;
    
    private String nomProprio;
    
    private String numTelClient;
    
    private String circuit;
    
    private String cd;

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
        return "CommandeDto{" + "client=" + client + ", username=" + username + ", quantite=" + quantite + ", prixTotal=" + prixTotal + ", sup=" + sup + ", telSup=" + telSup + ", mailSup=" + mailSup + ", merch=" + merch + ", telMerch=" + telMerch + ", mailMerch=" + mailMerch + ", raisonSociale=" + raisonSociale + ", nomProprio=" + nomProprio + ", numTelClient=" + numTelClient + ", circuit=" + circuit + ", cd=" + cd + '}';
    }

    

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(int prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getSup() {
        return sup;
    }

    public void setSup(String sup) {
        this.sup = sup;
    }

    public String getTelSup() {
        return telSup;
    }

    public void setTelSup(String telSup) {
        this.telSup = telSup;
    }

    public String getMailSup() {
        return mailSup;
    }

    public void setMailSup(String mailSup) {
        this.mailSup = mailSup;
    }

    public String getMerch() {
        return merch;
    }

    public void setMerch(String merch) {
        this.merch = merch;
    }

    public String getTelMerch() {
        return telMerch;
    }

    public void setTelMerch(String telMerch) {
        this.telMerch = telMerch;
    }

    public String getMailMerch() {
        return mailMerch;
    }

    public void setMailMerch(String mailMerch) {
        this.mailMerch = mailMerch;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getNomProprio() {
        return nomProprio;
    }

    public void setNomProprio(String nomProprio) {
        this.nomProprio = nomProprio;
    }

    public String getNumTelClient() {
        return numTelClient;
    }

    public void setNumTelClient(String numTelClient) {
        this.numTelClient = numTelClient;
    }

    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }
    
    
    
    
    
    
}
