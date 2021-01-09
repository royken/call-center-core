package com.bracongo.callcenter.repository;

import com.bracongo.callcenter.entities.Commande;
import com.bracongo.callcenter.entities.CommandeItem;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author vr.kenfack
 */
public interface ICommandeItemRepository extends JpaRepository<CommandeItem, Long>{
    
    public List<CommandeItem> findByCommande(Commande commande);
    
    @Query("select item from CommandeItem item where item.commande.dateCommande between :debut and :fin")
    public List<CommandeItem> findAllItemBetweenDate(@Param("debut") Date debut, @Param("fin")Date fin);
    
}
