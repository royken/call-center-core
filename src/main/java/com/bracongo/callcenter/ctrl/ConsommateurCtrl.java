package com.bracongo.callcenter.ctrl;

import com.bracongo.callcenter.entities.Consommateur;
import com.bracongo.callcenter.service.IConsommateurService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vr.kenfack
 */
@RestController
@RequestMapping("/consommateurs")
@CrossOrigin(origins = "*")
public class ConsommateurCtrl {

    @Autowired
    private IConsommateurService consommateurService;

    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.POST, value = "/{username}")
    public ResponseEntity<Consommateur> register(@RequestBody Consommateur consommateur, @PathVariable("username") String username) {
        Consommateur result = consommateurService.saveOrUpdateCommateur(consommateur, username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Consommateur>> getAllConsumers() {
        List<Consommateur> result = consommateurService.getAllConsommateur();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
