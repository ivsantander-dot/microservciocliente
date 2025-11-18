package com.example.microservciocliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.microservciocliente.model.Cliente;
import com.example.microservciocliente.repository.ClienteRepository;

public class ClienteService {
    @Autowired
    private ClienteRepository  clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id){
        clienteRepository.deleteById(id);
    }
}
