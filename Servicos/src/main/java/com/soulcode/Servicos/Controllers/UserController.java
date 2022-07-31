package com.soulcode.Servicos.Controllers;

import com.soulcode.Servicos.Models.User;
import com.soulcode.Servicos.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("servicos")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/usuarios")
    public List<User> usuarios() {
        return userService.listar();
    }

    @PostMapping("/usuarios")
    public ResponseEntity<User> inserirUsuario(@RequestBody User user) {
        String senhaCodificada = passwordEncoder.encode(user.getPassword());
        user.setPassword(senhaCodificada);
        user = userService.cadastrar(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/usuarios")
    public ResponseEntity<User> alterarSenha(@RequestBody User usuario,
                                             @RequestHeader("Authorization") String headers){
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        userService.alterarSenha(usuario, headers);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("usuarios/desabilitar/{login}")
    public ResponseEntity<User> desabilitarUsuario(@PathVariable String login,
                                                   @RequestHeader ("Authorization") String headers) {
        userService.desabilitarUsuario(login, headers);
        return ResponseEntity.ok().build();
    }
}
