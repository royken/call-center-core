package com.bracongo.callcenter.ctrl;

import com.bracongo.callcenter.entities.Commande;
import com.bracongo.callcenter.entities.dto.CommandeDto;
import com.bracongo.callcenter.entities.dto.CommandeStatutUpdateDto;
import com.bracongo.callcenter.entities.dto.DateDto;
import com.bracongo.callcenter.service.IcommandeService;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    
    @RequestMapping(method = RequestMethod.POST, value = "dates")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Commande>> getAllCommandeBetweenDates(@RequestBody DateDto dates){
        List<Commande> result = commandeService.getAllCommandesBetweenDates(dates.getDebut(), dates.getFin());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "update")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Commande> updateCommandeStatut(@RequestBody CommandeStatutUpdateDto statut){
        Commande result = commandeService.updateStatut(statut);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "exportCommande")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> exportReport(HttpServletRequest request, HttpServletResponse response, @RequestBody DateDto dates) {
        try {
            System.out.println("travail");
            OutputStream out;
            LocalDateTime localDate = LocalDateTime.now();
            localDate = localDate.minusDays(1);
            String fileName = "Rapport_Commandes_" + localDate.getDayOfMonth() + "_" + localDate.getMonthValue() + "_" + localDate.getYear() + ".xlsx";
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            out = response.getOutputStream();
            byte[] contentReturn = commandeService.exportCommandeReport(out, dates.getDebut(), dates.getFin());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            return new ResponseEntity<byte[]>(contentReturn, headers, HttpStatus.OK);
        } catch (IOException ex) {
            Logger.getLogger(CommandeCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
