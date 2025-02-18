package me.dio.controller;

import jakarta.validation.Valid;
import me.dio.domain.dto.AuthenticationDTO;
import me.dio.domain.dto.LoginResponseDTO;
import me.dio.domain.dto.RegisterDTO;
import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.TokenService;
import me.dio.service.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
       var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
       var auth = this.authenticationManager.authenticate(usernamePassword);

       var token = tokenService.generateToken((User) auth.getPrincipal());

       return  ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (repository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();


        if (data.password() == null || data.password().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("A senha não pode ser vazia.");
        }

        String encryptedPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.email(), encryptedPassword, data.role());

        repository.save(newUser);
        return ResponseEntity.ok().build();
    }

}
