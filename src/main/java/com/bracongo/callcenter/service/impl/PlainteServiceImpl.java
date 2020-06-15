package com.bracongo.callcenter.service.impl;

import com.bracongo.callcenter.entities.Plainte;
import com.bracongo.callcenter.entities.Utilisateur;
import com.bracongo.callcenter.entities.dto.PlainteDto;
import com.bracongo.callcenter.repository.IPlainteRepository;
import com.bracongo.callcenter.repository.IUtilisateurRepository;
import com.bracongo.callcenter.service.IPlainteService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vr.kenfack
 */
@Service
@Transactional
public class PlainteServiceImpl implements IPlainteService{
    
    @Autowired
    private IPlainteRepository plainteRepository;
    
    @Autowired
    private IUtilisateurRepository utilisateurRepository;

    @Override
    public Plainte saveOrUpdatePlainte(PlainteDto plainteDto) {
        Utilisateur u = utilisateurRepository.findByUsername(plainteDto.getUsername().trim());
        if(u != null){
            Plainte p = new Plainte();
            p.setUtilisateur(u);
            p.setDatePlainte(new Date());
            p.setDescription(plainteDto.getDescription());
            p.setTypePlainte(plainteDto.getTypePlainte());
            p.setStatut("");
            p.setClient(plainteDto.getClient());
            return plainteRepository.save(p);
        }
        return null;
    }

    @Override
    public List<Plainte> getAllPlaintes() {
        return plainteRepository.findAll();
    }
    
}
