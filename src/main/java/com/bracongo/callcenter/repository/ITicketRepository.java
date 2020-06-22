package com.bracongo.callcenter.repository;

import com.bracongo.callcenter.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Long>{
    
}
