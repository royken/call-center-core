package com.bracongo.callcenter.entities.dto;

import com.bracongo.callcenter.entities.Ticket;
import com.bracongo.callcenter.entities.TypeAppel;
import com.bracongo.callcenter.entities.TypeAppelant;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author vr.kenfack
 */
public class TicketDto implements Serializable{
    
    private Long id;
    
    private TypeAppel typeAppel;
    
    private TypeAppelant typeAppelant;
    
    private String idAppelant;
    
    private String username;
    
    private Date dateTicket;

    public TicketDto(Ticket ticket) {
        this.id = ticket.getId();
        this.typeAppel = ticket.getTypeAppel();
        this.typeAppelant = ticket.getTypeAppelant();
        this.idAppelant = ticket.getIdAppelant();
        this.username = ticket.getUtilisateur().getUsername();
        this.dateTicket = ticket.getDateTicket();
    }

    public TicketDto() {
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeAppel getTypeAppel() {
        return typeAppel;
    }

    public void setTypeAppel(TypeAppel typeAppel) {
        this.typeAppel = typeAppel;
    }

    public TypeAppelant getTypeAppelant() {
        return typeAppelant;
    }

    public void setTypeAppelant(TypeAppelant typeAppelant) {
        this.typeAppelant = typeAppelant;
    }

    public String getIdAppelant() {
        return idAppelant;
    }

    public void setIdAppelant(String idAppelant) {
        this.idAppelant = idAppelant;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateTicket() {
        return dateTicket;
    }

    public void setDateTicket(Date dateTicket) {
        this.dateTicket = dateTicket;
    }

    
    
    
}
