package com.bracongo.callcenter.service;

import com.bracongo.callcenter.entities.Commande;
import com.bracongo.callcenter.entities.dto.CommandeDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vr.kenfack
 */
@Transactional
public interface IcommandeService {
    
    public Commande saveCommande(CommandeDto commande);
    
    public Commande getCommandeById(Long id);
    
    public void deleteCommande(Long id);
    
    public List<Commande> getAllCommandes();
    
}
