package com.bracongo.callcenter.ctrl;

import com.bracongo.callcenter.entities.Ticket;
import com.bracongo.callcenter.entities.dto.TicketDto;
import com.bracongo.callcenter.service.ITicketService;
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
@RequestMapping("/tickets")
@CrossOrigin(origins = "*")
public class TicketCtrl {
    
    @Autowired
    private ITicketService ticketService;
    
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.POST, value = "/{username}")
    public ResponseEntity<TicketDto> register(@RequestBody Ticket ticket, @PathVariable("username") String username) {
        TicketDto result = ticketService.saveOrUpdateTicket(ticket, username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TicketDto>> getAllConsumers() {
        List<TicketDto> result = ticketService.getAllTicket();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
}
