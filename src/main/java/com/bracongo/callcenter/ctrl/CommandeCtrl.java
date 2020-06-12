package com.bracongo.callcenter.ctrl;

import com.bracongo.callcenter.entities.Commande;
import com.bracongo.callcenter.entities.dto.CommandeDto;
import com.bracongo.callcenter.service.IcommandeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vr.kenfack
 */
@RestController
@RequestMapping("/commandes")
@CrossOrigin(origins = "*")
public class CommandeCtrl {
    
    @Autowired
    private IcommandeService commandeService;
    
    @RequestMapping(method = RequestMethod.POST, value = "add")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Commande> addCommande(@RequestBody CommandeDto commande){
        Commande result = commandeService.saveCommande(commande);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Commande>> getAllCommande(){
        List<Commande> result = commandeService.getAllCommandes();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
}
