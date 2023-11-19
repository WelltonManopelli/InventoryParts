package com.uniara.inventory.controller;

import com.uniara.inventory.domain.user.Login;
import com.uniara.inventory.domain.user.LoginResponse;
import com.uniara.inventory.domain.user.Register;
import com.uniara.inventory.domain.user.Users;
import com.uniara.inventory.repositories.UsersRepository;
import com.uniara.inventory.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsersRepository repository;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid Login login){
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.login(),
                        login.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Users) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponse(token));

    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid Register data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Users newUser = new Users(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
