package br.com.netpos.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "\"user\"")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    //TODO - anotação hibernate Unique
    private String email;
    private String password;
}
