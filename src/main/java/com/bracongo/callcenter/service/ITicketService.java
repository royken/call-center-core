package com.bracongo.callcenter.service;

import com.bracongo.callcenter.entities.Ticket;
import com.bracongo.callcenter.entities.dto.TicketDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vr.kenfack
 */
@Transactional
public interface ITicketService {
    
    public TicketDto saveOrUpdateTicket(Ticket ticket, String username);
    
    public List<TicketDto> getAllTicket();
}
