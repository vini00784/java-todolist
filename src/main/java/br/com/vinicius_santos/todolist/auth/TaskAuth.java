package br.com.vinicius_santos.todolist.auth;

import java.util.Base64;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.vinicius_santos.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
/*
 * Necessário para que o Spring gerencie essa classe
*/
public class TaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                var servletPath = request.getServletPath();
                
                if(servletPath.equals("/task/create")) {
                    /*
                     * 1. Pegar a autenticação (user e password);
                     * 2. Validar usuário;
                     * 3. Validar senha;
                    */
    
                    // 1.
                    var authorization = request.getHeader("Authorization");
                    var encodedAuth = authorization.split(" ")[1];
    
                    byte[] decodedAuth = Base64.getDecoder().decode(encodedAuth);
    
                    var authString = new String(decodedAuth);
    
                    String[] authCredentials = authString.split(":");
                    String username = authCredentials[0];
                    String password = authCredentials[1];
    
                    // 2.
                    var user = this.userRepository.findByUsername(username);
                    if(user == null) {
                        response.sendError(401);
                    } else {
                        // 3.
                        var verifyPassword = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified;
                        if(verifyPassword) {
                            filterChain.doFilter(request, response);
                        } else {
                            response.sendError(401);
                        }
                    }
                } else {
                    filterChain.doFilter(request, response);
                }

    }

   
    
}
