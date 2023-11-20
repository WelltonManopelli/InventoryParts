package com.uniara.inventory.controller;

import com.uniara.inventory.domain.dealer.Dealer;
import com.uniara.inventory.domain.dealer.DealerDTO;
import com.uniara.inventory.domain.user.Login;
import com.uniara.inventory.domain.user.LoginResponse;
import com.uniara.inventory.domain.user.Register;
import com.uniara.inventory.domain.user.Users;
import com.uniara.inventory.repositories.DealerRepository;
import com.uniara.inventory.repositories.UsersRepository;
import com.uniara.inventory.security.TokenService;
import jakarta.transaction.Transactional;
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

import java.util.Optional;


@RestController
@RequestMapping
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsersRepository repository;

    @Autowired
    private DealerRepository dealerRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid Login login) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.login(),
                login.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Users) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponse(token));

    }

    @PostMapping("/register/user")
    public ResponseEntity registerUser(@RequestBody @Valid Register data) {
        if (this.repository.findByLogin(data.login()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Optional<Dealer> dealer = this.dealerRepository.findById(data.cod());

        if (dealer.isPresent()) {
            Users newUser = new Users(data, encryptedPassword, dealer.get());
            this.repository.save(newUser);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Dealer sem cadastrado");
        }
    }

    @PostMapping("/register/dealer")
    @Transactional
    public ResponseEntity registerDealer(@RequestBody @Valid DealerDTO data) {

        Optional<Dealer> byId = this.dealerRepository.findById(data.cod());
        if (byId.isPresent()) {
            return ResponseEntity.badRequest().body("Dealer já cadastrado");
        } else {
            Dealer dealer = new Dealer(data);
            dealerRepository.save(dealer);
            return ResponseEntity.ok().build();
        }
    }
}
