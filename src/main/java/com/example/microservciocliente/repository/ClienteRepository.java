package com.example.microservciocliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservciocliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
