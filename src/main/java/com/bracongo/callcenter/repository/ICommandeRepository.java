package com.bracongo.callcenter.repository;

import com.bracongo.callcenter.entities.Commande;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface ICommandeRepository extends JpaRepository<Commande, Long>{
    
    public List<Commande> findAllByDateCommandeBetween(Date debut, Date fin);
}
