package br.com.vinicius_santos.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data 
/* 
    O decorator @Data renderiza automaticamente os getters e setters de todos os atributos da classe;
    No caso de precisar em apenas um dos atributos, basta colocar um @Getter e/ou @Setter em cima do atributo em específico;
*/
@Entity(name = "tbl_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    /* 
     * Os atributos diferentes do UUID, não necessitam de um decorator, pois com o @Entity, ele já entende que eles são atributos da tabela e seus respectivos nomes
     * No caso de precisar alterar algo, é só utilizar o decorator @Column(name = "columnName")
    */
}
