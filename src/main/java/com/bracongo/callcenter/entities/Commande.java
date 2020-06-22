package com.bracongo.callcenter.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "BD_COMMANDE", catalog = "BD_CALL_CENTER", schema = "dbo")
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter
public class Commande implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "DATE_COMMANDE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCommande;
    
    @OneToMany(mappedBy = "commande")
    @JsonIgnore
    private List<CommandeItem> commandeItems;
    
    @Column(name = "CLIENT")
    private String client;
    
    @Column(name = "STATUT")
    private String statut;
    
    @ManyToOne
    @JoinColumn(name = "UTILISATEUR")
    private Utilisateur utilisateur;
    
    @ManyToOne
    @JoinColumn(name = "TICKET")
    private Ticket ticket;

    public Commande() {
    }
    
}
