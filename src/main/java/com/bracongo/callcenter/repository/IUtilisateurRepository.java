package com.bracongo.callcenter.repository;

import com.bracongo.callcenter.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Long>{
    
    public Utilisateur findByUsername(String username);
    
    public Utilisateur findUtilisateurById(Long id);
    
}
