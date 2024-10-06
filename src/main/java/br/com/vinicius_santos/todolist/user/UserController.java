package br.com.vinicius_santos.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired // Usado para que o próprio SpringBoot gerencie o ciclo de vida do meu repository
    private IUserRepository userRepository;
    
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody UserModel user) {
        var foundedUser = this.userRepository.findByUsername(user.getUsername());

        if(foundedUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe na base de dados!");
        }

        var hashedPassword = BCrypt.withDefaults()
        .hashToString(12, user.getPassword().toCharArray());

        user.setPassword(hashedPassword);

        var createdUser = this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
