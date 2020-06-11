package com.bracongo.callcenter.repository;

import com.bracongo.callcenter.entities.CommandeItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vr.kenfack
 */
public interface ICommandeItemRepository extends JpaRepository<CommandeItem, Long>{
    
}
