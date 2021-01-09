package com.bracongo.callcenter.service.impl;

import com.bracongo.callcenter.entities.Ticket;
import com.bracongo.callcenter.entities.Utilisateur;
import com.bracongo.callcenter.entities.dto.TicketDto;
import com.bracongo.callcenter.repository.ITicketRepository;
import com.bracongo.callcenter.repository.IUtilisateurRepository;
import com.bracongo.callcenter.service.ITicketService;
import java.util.ArrayList;
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
    public TicketDto saveOrUpdateTicket(Ticket ticket, String username) {
        Utilisateur u = utilisateurRepository.findByUsername(username);
        ticket.setUtilisateur(u);
        ticket.setDateTicket(new Date());
        Ticket ticket_ =  ticketRepository.save(ticket);
        TicketDto result = new TicketDto();
        result.setId(ticket_.getId());
        result.setIdAppelant(ticket_.getIdAppelant());
        result.setTypeAppel(ticket_.getTypeAppel());
        result.setUsername(ticket_.getUtilisateur().getUsername());
        result.setTypeAppelant(ticket_.getTypeAppelant());
        result.setDateTicket(ticket_.getDateTicket());
        return result;
    }

    @Override
    public List<TicketDto> getAllTicket() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketDto> result = new ArrayList<>();
        tickets.stream().map((ticket) -> new TicketDto(ticket)).forEachOrdered((temp) -> {
            result.add(temp);
        });
        return result;
    }
    
}
