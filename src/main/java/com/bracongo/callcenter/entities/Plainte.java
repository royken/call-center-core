package com.bracongo.callcenter.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author vr.kenfack
 */
@Entity
@Table(name = "BD_PLAINTE", catalog = "BD_CALL_CENTER", schema = "dbo")
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter
public class Plainte implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "DATE_PLAINTE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date datePlainte;
    
    @Column(name = "STATUT")
    private String statut;
    
    @Column(name = "TYPE")
    private String typePlainte;
    
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "CLIENT")
    private String client;
    
    @ManyToOne
    @JoinColumn(name = "UTILISATEUR")
    private Utilisateur utilisateur;

    public Plainte() {
    }
    
}
