package me.dio.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.dio.exception.ErroAutenticacao;
import me.dio.exception.RegraNegocioException;
import me.dio.domain.dto.AuthenticationDTO;
import me.dio.domain.dto.UserDTO;
import me.dio.domain.model.entity.User;
import me.dio.service.LancamentoService;
import me.dio.service.TokenService;
import me.dio.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "usuario", description = "Controlador para salvar, autenticar, e obter saldo do usuario")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService service;
    private final LancamentoService lancamentoService;
    private  final TokenService jwtService;

    @PostMapping("/autenticar")
    @Operation(summary = "autentica usuarios", description = "metodo para autenticar dados de usuarios")
    @ApiResponse(responseCode = "200" , description = "usuario autenticado")
    @ApiResponse(responseCode = "400", description = "Usuario não cadastrado")
    public ResponseEntity<?> autenticar(@RequestBody UserDTO dto){
        try{
            User usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
            String token = jwtService.generateToken(usuarioAutenticado);
            AuthenticationDTO tokenDTO = new AuthenticationDTO(usuarioAutenticado.getNome(), token);
            return  ResponseEntity.ok(tokenDTO);
        }catch (ErroAutenticacao e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "salva usuarios", description = "metodo para salvar dados de usuarios")
    @ApiResponse(responseCode = "201" , description = "usuario salvo")
    @ApiResponse(responseCode = "400", description = "Usuario ja cadastrado")
    public ResponseEntity salvar(@RequestBody UserDTO dto){

        User usuario = User.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .build();
        try {
            User usuarioSalvo = service.salvarUsuario(usuario);
            return  new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        }catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}/saldo")
    public ResponseEntity obterSaldo(@PathVariable("id") Long id){
        Optional<User> usuario = service.obterPorId(id);

        if (!usuario.isPresent()){
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        BigDecimal saldo = lancamentoService.obterSaldoPorUsuario(id);
        return ResponseEntity.ok(saldo);
    }
}
