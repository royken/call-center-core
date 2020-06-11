package com.bracongo.callcenter.ctrl;

import com.bracongo.callcenter.entities.Utilisateur;
import com.bracongo.callcenter.entities.dto.AuthBody;
import com.bracongo.callcenter.entities.dto.LoginResponse;
import com.bracongo.callcenter.repository.IUtilisateurRepository;
import com.bracongo.callcenter.securityconfigs.JwtTokenProvider;
import com.bracongo.callcenter.service.impl.CustomUserDetailsService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vr.kenfack
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UtilisateurCtrl {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    IUtilisateurRepository utilisateurRepository;
    
    @Autowired
    private CustomUserDetailsService userService;
    
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthBody data) {
        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            Utilisateur u = utilisateurRepository.findByUsername(username);
            String token = jwtTokenProvider.createToken(username, u.getRole());
            LoginResponse response = new LoginResponse(username, token);
            response.setStatus(true);
            response.setRole(u.getRole());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AuthenticationException e) {
            LoginResponse response = new LoginResponse(null, null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            //throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    @SuppressWarnings("rawtypes")
   @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity register(@RequestBody Utilisateur user) {
        System.out.println("Registering");
        Utilisateur userExists = utilisateurRepository.findByUsername(user.getUsername());
        if (userExists != null) {
            throw new BadCredentialsException("User with username: " + user.getUsername() + " already exists");
        }
        userService.saveOrUpdateUtilisateur(user);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");
        return ok(model);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/utilisateurs")
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs(){
        List<Utilisateur> result = userService.getAllUtilisateurs();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/utilisateurs/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable("id")  Long id){
        Utilisateur result = userService.getUtilisateurById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
}
