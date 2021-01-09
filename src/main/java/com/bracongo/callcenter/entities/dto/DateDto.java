package com.bracongo.callcenter.entities.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author vr.kenfack
 */
public class DateDto implements Serializable{
    
    private Date debut;
    
    private Date fin;

    public DateDto() {
    }
    
    

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }
    
    
}
