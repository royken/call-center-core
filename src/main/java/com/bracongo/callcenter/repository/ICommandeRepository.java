package com.bracongo.callcenter.repository;

import com.bracongo.callcenter.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface ICommandeRepository extends JpaRepository<Commande, Long>{
    
}
