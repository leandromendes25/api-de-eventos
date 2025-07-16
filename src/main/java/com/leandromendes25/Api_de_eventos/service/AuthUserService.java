package com.leandromendes25.Api_de_eventos.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.leandromendes25.Api_de_eventos.dto.user.AuthUserDTO;
import com.leandromendes25.Api_de_eventos.exceptions.UserNotFoundException;
import com.leandromendes25.Api_de_eventos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthUserService {
    @Autowired
    private UserRepository usrRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${security.token.secret.user}")
    private String secretKey;

    public String login(AuthUserDTO auth) throws AuthenticationException {
        var usr = usrRepo.findByEmail(auth.email()).orElseThrow(() -> new UserNotFoundException("User not found"));
        var passwordMatches = passwordEncoder.matches(auth.password(), usr.getPassword());
        if(!passwordMatches){
            throw new AuthenticationException();
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create().withIssuer("eventos-api").withSubject(usr.getId().toString()).sign(algorithm);
    }
}
