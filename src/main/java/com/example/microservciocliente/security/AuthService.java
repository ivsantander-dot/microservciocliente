package com.example.microservciocliente.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.microservciocliente.model.Cliente;
import com.example.microservciocliente.model.Rol;
import com.example.microservciocliente.repository.ClienteRepository;
import com.example.microservciocliente.security.dto.AuthResponse;
import com.example.microservciocliente.security.dto.LoginRequest;
import com.example.microservciocliente.security.dto.RegisterRequest;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider JwtTokenProvider;

    // REGISTER

    public AuthResponse register(RegisterRequest request) {
        if(clienteRepository.findByCorreo(request.getUsername()).isPresent()) {
            throw new RuntimeException("El correo ya existe");
        }

        Cliente cliente = new Cliente();
        cliente.setCorreo(request.getUsername());
        cliente.setContrasenia(passwordEncoder.encode(request.getPassword()));
        cliente.setNombre("Nuevo Usuario");
        cliente.setRut(request.getUsername());
        cliente.setRegion("N/A");
        cliente.setComuna("N/A");
        cliente.setTelefono("N/A");
        cliente.setFechaNacimiento(new java.util.Date());
        cliente.setRole(Rol.valueOf(request.getRole())); // USER o ADMIN

        clienteRepository.save(cliente);

        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = JwtTokenProvider.generateToken(auth);
        return new AuthResponse(token);

    }

    public AuthResponse login(LoginRequest request) {
    Authentication auth = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );

    String token = JwtTokenProvider.generateToken(auth);
    return new AuthResponse(token);
}
    
}
