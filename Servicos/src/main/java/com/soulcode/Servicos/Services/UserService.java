package com.soulcode.Servicos.Services;

import com.soulcode.Servicos.Models.User;
import com.soulcode.Servicos.Repositories.UserRepository;
import com.soulcode.Servicos.Security.AuthUserDetail;
import com.soulcode.Servicos.Services.Exceptions.EntityNotFoundException;
import com.soulcode.Servicos.Util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    public List<User> listar() {
        return userRepository.findAll();
    }

    public User cadastrar(User user) {
        return userRepository.save(user);
    }

    public User alterarSenha(String password, String login, String headers) {

        if (headers != null && headers.startsWith("Bearer")) {
            String email = jwtUtils.getLogin(headers.substring(7));
            Optional<User> user = userRepository.findByLogin(login);

            if(email.equals(user.get().getLogin())) {
                user.get().setPassword(password);
                return userRepository.save(user.get());
            }
        }
            throw new EntityNotFoundException("Usuário não autorizado");
    }
}
