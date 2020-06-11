package com.bracongo.callcenter.service.impl;

import com.bracongo.callcenter.entities.Utilisateur;
import com.bracongo.callcenter.repository.IUtilisateurRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Valmy Roi Kenfack <vr.kenfack at bracongo.cd>
 */
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService{
    
    @Autowired
    private IUtilisateurRepository utilisateurRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Utilisateur findByUsername(String usersame){
        return utilisateurRepository.findByUsername(usersame.trim());
    }
    
    public Utilisateur saveOrUpdateUtilisateur(Utilisateur user){
        System.out.println("USER : "+ user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActif(true);
        //Role role = roleRepository.findByRole("ADMIN");
        //user.setRoles(new HashSet<>(Arrays.asList(role)));
        return utilisateurRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findByUsername(username.trim());
        if(user != null){
            List<GrantedAuthority> authorities  = getUtilisateurAuthority(user.getRole());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }
    
    private List<GrantedAuthority> getUtilisateurAuthority(String role) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(Utilisateur user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findUtilisateurById(id);
    }

    public Utilisateur createOrUpdateUtilisateur(Utilisateur utilisateur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteUtilisateur(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Utilisateur getUtilisateurByUsername(String username) {
        return utilisateurRepository.findByUsername(username.trim());
    }
    
}
