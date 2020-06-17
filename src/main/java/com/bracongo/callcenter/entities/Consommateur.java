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
@Table(name = "BD_CONSOMMATEUR", catalog = "BD_CALL_CENTER", schema = "dbo")
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter
public class Consommateur implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "NOM")
    private String nom;
    
    @Column(name = "NUMTEL")
    private String numTel;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "QUARTIER")
    private String quartier;
    
    @Column(name = "COMMUNE")
    private String commune;
    
    @Column(name = "VILLE")
    private String ville;
    
    @Column(name = "DATE_ENREGISTREMENT")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateEnregistrement;
    
    @ManyToOne
    @JoinColumn(name = "UTILISATEUR")
    private Utilisateur utilisateur;

    public Consommateur() {
    }
    
}
