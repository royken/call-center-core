package com.bracongo.callcenter.service.impl;

import com.bracongo.callcenter.entities.Utilisateur;
import com.bracongo.callcenter.repository.IUtilisateurRepository;
import com.bracongo.callcenter.service.IUtilisateurService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vr.kenfack
 */
@Service
@Transactional
public class UtilisateurServiceImpl implements IUtilisateurService{
    
    @Autowired
    private IUtilisateurRepository utilisateurRepository;

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findUtilisateurById(id);
    }

    @Override
    public Utilisateur getUtilisateurByUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

    @Override
    public Utilisateur createOrUpdateUtilisateur(Utilisateur utilisateur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUtilisateur(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
