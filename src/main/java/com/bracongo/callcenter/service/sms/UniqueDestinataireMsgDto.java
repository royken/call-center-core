package com.bracongo.callcenter.service.sms;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class UniqueDestinataireMsgDto implements Serializable{
    
    private String numeroDestinataire;
    
    private String plateforme;
    
    private String motif;
    
    private String message;

    public String getNumeroDestinataire() {
        return numeroDestinataire;
    }

    public void setNumeroDestinataire(String numeroDestinataire) {
        this.numeroDestinataire = numeroDestinataire;
    }

    public String getPlateforme() {
        return plateforme;
    }

    public void setPlateforme(String plateforme) {
        this.plateforme = plateforme;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
