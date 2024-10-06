package br.com.vinicius_santos.todolist.user;

import lombok.Data;

@Data 
/* 
    Esse decorator renderiza automaticamente os getters e setters de todos os atributos da classe;
    No caso de precisar em apenas um dos atributos, basta colocar um @Getter e/ou @Setter em cima do atributo em específico;
*/
public class UserModel {
    private String username;
    private String name;
    private String password;
}
