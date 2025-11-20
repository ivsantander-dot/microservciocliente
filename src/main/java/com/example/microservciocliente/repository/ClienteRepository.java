package com.example.microservciocliente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservciocliente.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    Optional<Cliente> findByCorreo(String correo); //Sirve para el login y que busque el usuario por correo

    boolean exisexistsByCorreo(String correo); // Sirve para validar el registro

}
