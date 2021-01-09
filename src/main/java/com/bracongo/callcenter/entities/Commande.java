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
    private List<CommandeItem> commandeItems;
    
    @Column(name = "CLIENT")
    private String client;
    
    @Column(name = "RAISON_SOCIALE")
    private String raisonSociale;
    
    @Column(name = "NOM_PROPRIO")
    private String nomProprio;
    
    @Column(name = "TEL_CLIENT")
    private String numTelClient;
    
    @Column(name = "STATUT")
    private String statut;
    
    @ManyToOne
    @JoinColumn(name = "UTILISATEUR")
    private Utilisateur utilisateur;
    
    @ManyToOne
    @JoinColumn(name = "TICKET")
    private Ticket ticket;
    
    @Column(name = "QUANTITE")
    private int quantite;
    
    @Column(name = "PRIX_TOTAL")
    private int prixTotal;
    
    @Column(name = "ANNULEE")
    private boolean active;
    
    @Column(name = "DATE_ANNULATION")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateAnnulation;
    
    @Column(name = "USER_ANNULATION")
    private String userAnnulateur;
    
    @Column(name = "DATE_LIVRAISON")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateLivraison;
    
    @Column(name = "USER_LIVRAISON")
    private String userLivraison;
    
    @Column(name = "MERCH")
    private String merch;
    
    @Column(name = "MAIL_MERCH")
    private String mailMerch;
    
    @Column(name = "TEL_MERCH")
    private String telMerch;
    
    @Column(name = "SUP")
    private String sup;
    
    @Column(name = "MAIL_SUP")
    private String mailSup;
    
    @Column(name = "TEL_SUP")
    private String telSup;
    
    @Column(name = "CIRCUIT")
    private String circuit;
    
    @Column(name = "CD")
    private String cd;

    public Commande() {
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", dateCommande=" + dateCommande + ", commandeItems=" + commandeItems + ", client=" + client + ", raisonSociale=" + raisonSociale + ", nomProprio=" + nomProprio + ", numTelClient=" + numTelClient + ", statut=" + statut + ", utilisateur=" + utilisateur + ", ticket=" + ticket + ", quantite=" + quantite + ", prixTotal=" + prixTotal + ", active=" + active + ", dateAnnulation=" + dateAnnulation + ", userAnnulateur=" + userAnnulateur + ", dateLivraison=" + dateLivraison + ", userLivraison=" + userLivraison + ", merch=" + merch + ", mailMerch=" + mailMerch + ", telMerch=" + telMerch + ", sup=" + sup + ", mailSup=" + mailSup + ", telSup=" + telSup + ", circuit=" + circuit + ", cd=" + cd + '}';
    }
    
    
    
}
