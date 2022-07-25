package br.com.netpos.api;

import br.com.netpos.domain.User;
import br.com.netpos.gateway.h2.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value= "/user")
@RequestMapping("/user")
public class UserAccountApi {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> listUsers(){
        List<User> all = userRepository.findAll();
        if (all.isEmpty()){
          return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(all);
        }
    }

    @GetMapping
    @RequestMapping("/{id}")
    @ApiOperation(value = "Busca usuário por id")
    public ResponseEntity<User> getByID(@PathVariable("id") final Long id){
        final Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ApiOperation(value = "Cria um usuário", notes = "Preencher:\n- name\n- email\n- password")
    public ResponseEntity<User> createUser(@RequestBody final UserRequest userRequest) {
        final User userCreated = userRepository.save(UserRequest.toUser(userRequest));
        return ResponseEntity.ok(userCreated);
    }
}

    @Data
    class UserRequest {
        private String name;
        @Column(unique=true)
        private String email;
        private String password;

        public static User toUser(final UserRequest userRequest) {
            User user = new User();
            user.setEmail(userRequest.getEmail());
            user.setName(userRequest.getName());
            user.setPassword(userRequest.getPassword());
            return user;
        }
    }
