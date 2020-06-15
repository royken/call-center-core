package com.bracongo.callcenter.service.impl;

import com.bracongo.callcenter.entities.Commande;
import com.bracongo.callcenter.entities.CommandeItem;
import com.bracongo.callcenter.entities.Utilisateur;
import com.bracongo.callcenter.entities.dto.CommandeDto;
import com.bracongo.callcenter.repository.ICommandeItemRepository;
import com.bracongo.callcenter.repository.ICommandeRepository;
import com.bracongo.callcenter.repository.IUtilisateurRepository;
import com.bracongo.callcenter.service.IcommandeService;
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
public class CommandeServiceImpl implements IcommandeService {

    @Autowired
    private ICommandeRepository commandeRepository;

    @Autowired
    private ICommandeItemRepository commandeItemRepository;

    @Autowired
    private IUtilisateurRepository utilisateurRepository;

    @Override
    public Commande saveCommande(CommandeDto commande) {
        Utilisateur u = utilisateurRepository.findByUsername(commande.getUsername().trim());
        if (u != null) {
            System.out.println(commande);
            Commande commande_ = new Commande();
            commande_.setClient(commande.getClient());
            commande_.setDateCommande(new Date());
            commande_.setUtilisateur(u);
            commande_.setStatut("Initiee");
            commande_ = commandeRepository.save(commande_);
            List<CommandeItem> items = commande.getItems();
            for (CommandeItem item : items) {
                item.setCommande(commande_);
                commandeItemRepository.save(item);
            }
           // commande_.setClient(null);
           // commande_.setCommandeItems(null);
            return commande_;
        }
        return null;
    }

    @Override
    public Commande getCommandeById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCommande(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

}
