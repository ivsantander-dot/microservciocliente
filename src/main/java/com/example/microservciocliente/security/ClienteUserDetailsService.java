package com.example.microservciocliente.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.microservciocliente.model.Cliente;
import com.example.microservciocliente.repository.ClienteRepository;

@Service
public class ClienteUserDetailsService implements UserDetailsService{

    private final ClienteRepository clienteRepository;

    public ClienteUserDetailsService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByCorreo(correo).orElseThrow(() -> new UsernameNotFoundException("Usario no encontrado" + correo));

        return User.withUsername(cliente.getCorreo()).password(cliente.getContrasenia()).roles(cliente.getRole().name()).build();
    }
    
}
