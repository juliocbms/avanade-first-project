package me.dio.service.impl;

import jakarta.transaction.Transactional;
import me.dio.exception.ErroAutenticacao;
import me.dio.exception.RegraNegocioException;
import me.dio.domain.model.entity.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private UserRepository repository;

    public UserServiceImpl(UserRepository repository, PasswordEncoder encoder) {
        super();
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public User autenticar(String email, String senha) {
        Optional<User> user = repository.findByEmail(email);

        if(!user.isPresent()) {
            throw new ErroAutenticacao("Usuário não encontrado para o email informado.");
        }

        boolean senhasBatem = encoder.matches(senha,user.get().getSenha());

        if(!senhasBatem) {
            throw new ErroAutenticacao("Senha inválida.");
        }

        return user.get();
    }

    @Override
    @Transactional
    public User salvarUsuario(User user) {
        validarEmail(user.getEmail());
        criptografarSenha(user);
        return repository.save(user);
    }

    private void criptografarSenha(User user){
        String senha = user.getSenha();
        String senhaCripto = encoder.encode(senha);
        user.setSenha(senhaCripto);
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = repository.existsByEmail(email);
        if(existe) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
        }
    }

    @Override
    public Optional<User> obterPorId(Long id) {
        return repository.findById(id);
    }

}
