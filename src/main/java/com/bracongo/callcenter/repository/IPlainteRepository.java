package com.bracongo.callcenter.repository;

import com.bracongo.callcenter.entities.Plainte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vr.kenfack
 */
@Repository
public interface IPlainteRepository extends JpaRepository<Plainte, Long>{
    
}
