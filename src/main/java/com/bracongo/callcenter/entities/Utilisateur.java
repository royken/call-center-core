package com.bracongo.callcenter.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Valmy Roi Kenfack <vr.kenfack at bracongo.cd>
 */
@Entity
@Table(name = "BD_UTILISATEUR", catalog = "BD_CALL_CENTER", schema = "dbo")
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "NOM")
    private String nom;

    @Column(name = "NOM_UTILISATEUR")
    private String username;

    @Column(name = "MOT_DE_PASSE")
    private String password;

    @Column(name = "ACTIF")
    private boolean actif;
    
    @Column(name = "ROLE")
    private String role;
    
    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<Commande> commandes;
    
    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<Plainte> plaintes;

    public Utilisateur() {
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", username=" + username + ", password=" + password + ", actif=" + actif + ", role=" + role + '}';
    }

    
    
}
