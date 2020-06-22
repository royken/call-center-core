package com.bracongo.callcenter.repository;

import com.bracongo.callcenter.entities.Consommateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface IConsommateurRepository extends JpaRepository<Consommateur, Long>{
    
}
