package com.bracongo.callcenter.service;

import com.bracongo.callcenter.entities.Utilisateur;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vr.kenfack
 */
@Transactional
public interface IUtilisateurService {
    
    public List<Utilisateur> getAllUtilisateurs();
    
    public Utilisateur getUtilisateurById(Long id);
    
    public Utilisateur getUtilisateurByUsername(String username);
    
    public Utilisateur createOrUpdateUtilisateur(Utilisateur utilisateur);
    
    public void deleteUtilisateur(Long id);
    
}
