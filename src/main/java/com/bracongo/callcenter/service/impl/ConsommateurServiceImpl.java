package com.bracongo.callcenter.service.impl;

import com.bracongo.callcenter.entities.Consommateur;
import com.bracongo.callcenter.entities.Utilisateur;
import com.bracongo.callcenter.repository.IConsommateurRepository;
import com.bracongo.callcenter.repository.IUtilisateurRepository;
import com.bracongo.callcenter.service.IConsommateurService;
import java.util.Date;
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
public class ConsommateurServiceImpl implements IConsommateurService{
    
    @Autowired
    private IConsommateurRepository consommateurRepository;

    @Autowired
    private IUtilisateurRepository utilisateurRepository;
    
    @Override
    public Consommateur saveOrUpdateCommateur(Consommateur consommateur, String username) {
        consommateur.setDateEnregistrement(new Date());
        Utilisateur u = utilisateurRepository.findByUsername(username);
        consommateur.setUtilisateur(u);
        return consommateurRepository.save(consommateur);
    }

    @Override
    public Consommateur getConsommateurById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consommateur> getAllConsommateur() {
        return consommateurRepository.findAll();
    }
    
}
