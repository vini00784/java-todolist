package br.com.vinicius_santos.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, UUID>{
    UserModel findByUsername(String username); // Com o SpringData, é possível criar métodos além do que eles setta de forma automática
}
