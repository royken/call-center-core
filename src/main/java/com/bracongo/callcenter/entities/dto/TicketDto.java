package com.bracongo.callcenter.entities.dto;

import com.bracongo.callcenter.entities.TypeAppel;
import com.bracongo.callcenter.entities.TypeAppelant;
import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class TicketDto implements Serializable{
    
    private Long id;
    
    private TypeAppel typeAppel;
    
    private TypeAppelant typeAppelant;
    
    private Long idUtilisateur;

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

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    
    
    
}
