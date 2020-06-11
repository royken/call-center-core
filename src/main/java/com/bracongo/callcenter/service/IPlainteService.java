package com.bracongo.callcenter.service;

import com.bracongo.callcenter.entities.Plainte;
import com.bracongo.callcenter.entities.dto.PlainteDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vr.kenfack
 */
@Transactional
public interface IPlainteService {
    
    public Plainte saveOrUpdatePlainte(PlainteDto plainteDto);
    
    public List<Plainte> getAllPlaintes();
    
}
