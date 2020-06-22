package com.bracongo.callcenter.service.impl;

import com.bracongo.callcenter.entities.Ticket;
import com.bracongo.callcenter.entities.Utilisateur;
import com.bracongo.callcenter.repository.ITicketRepository;
import com.bracongo.callcenter.repository.IUtilisateurRepository;
import com.bracongo.callcenter.service.ITicketService;
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
public class TicketServiceImpl implements ITicketService{

    @Autowired
    private IUtilisateurRepository utilisateurRepository;
    
    @Autowired
    private ITicketRepository ticketRepository;
    
    @Override
    public Ticket saveOrUpdateTicket(Ticket ticket, String username) {
        Utilisateur u = utilisateurRepository.findByUsername(username);
        ticket.setUtilisateur(u);
        ticket.setDateTicket(new Date());
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getAllTicket() {
        return ticketRepository.findAll();
    }
    
}
