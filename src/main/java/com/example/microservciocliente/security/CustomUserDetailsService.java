package com.example.microservciocliente.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.microservciocliente.model.Cliente;
import com.example.microservciocliente.repository.ClienteRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository repo;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Cliente cliente = repo.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));

        return User.builder()
                .username(cliente.getCorreo())
                .password(cliente.getContrasenia())
                .roles(cliente.getRole().name()) // USER o ADMIN
                .build();
    }
}
