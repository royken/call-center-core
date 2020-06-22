package com.bracongo.callcenter.service;

import com.bracongo.callcenter.entities.Ticket;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vr.kenfack
 */
@Transactional
public interface ITicketService {
    
    public Ticket saveOrUpdateTicket(Ticket ticket, String username);
    
    public List<Ticket> getAllTicket();
}
