package com.bracongo.callcenter.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author vr.kenfack
 */
@Entity
@Table(name = "BD_COMMANDE_ITEM", catalog = "BD_CALL_CENTER", schema = "dbo")
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter
public class CommandeItem implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "COMMANDE")
    @JsonIgnore
    private Commande commande;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "NOM_PRODUIT")
    private String nomProduit;
    
    @Column(name = "QUANTITE")
    private int quantite;
    
    @Column(name = "CODE_PRODUIT")
    private String codeProduit;
    
    @Column(name = "PRIX_UNITAIRE")
    private int prixUnitaire;

    public CommandeItem() {
    }

    @Override
    public String toString() {
        return "CommandeItem{" + "id=" + id + ", nomProduit=" + nomProduit + ", quantite=" + quantite + ", codeProduit=" + codeProduit + ", prixUnitaire=" + prixUnitaire + '}';
    }
    
    
}
