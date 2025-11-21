package com.example.microservciocliente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.microservciocliente.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    Optional<Cliente> findByCorreo(String correo); //Sirve para el login y que busque el usuario por correo

    boolean existsByCorreo(String correo); // Sirve para validar el registro

}
