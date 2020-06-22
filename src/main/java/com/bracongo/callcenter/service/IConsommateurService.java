package com.bracongo.callcenter.service;

import com.bracongo.callcenter.entities.Consommateur;
import java.util.List;

/**
 *
 * @author vr.kenfack
 */
public interface IConsommateurService {
    
    public Consommateur saveOrUpdateCommateur(Consommateur consommateur, String username);
    
    public Consommateur getConsommateurById(Long id);
    
    public List<Consommateur> getAllConsommateur();
}
