package com.example.microservciocliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservciocliente.model.Cliente;
import com.example.microservciocliente.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository  clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente encontrarClienteId(long id) {
        return clienteRepository.findById(id).get();
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void borrar(Long id){
        clienteRepository.deleteById(id);
    }
}
