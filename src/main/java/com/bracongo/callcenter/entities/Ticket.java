package com.bracongo.callcenter.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author vr.kenfack
 */
@Entity
@Table(name = "BD_TICKET", catalog = "BD_CALL_CENTER", schema = "dbo")
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter
public class Ticket implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_APPEL")
    private TypeAppel typeAppel;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_APPELANT")
    private TypeAppelant typeAppelant;
    
    @ManyToOne
    @JoinColumn(name = "UTILISATEUR")
    @JsonIgnore
    private Utilisateur utilisateur;
    
    @Column(name = "DATE_TICKET")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateTicket;
    
    @Column(name = "ID_APPELENT")
    private String idAppelant;
    
    @OneToMany(mappedBy = "ticket")
    @JsonIgnore
    private List<Plainte> plaintes;
    
    @OneToMany(mappedBy = "ticket")
    @JsonIgnore
    private List<Commande> commandes;

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
