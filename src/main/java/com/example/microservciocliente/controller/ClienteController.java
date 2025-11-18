package com.example.microservciocliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservciocliente.model.Cliente;
import com.example.microservciocliente.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Gestion clientes", description = "Operaciones para gestionar clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Ver lista de clientes registrados", description = "Obtiene todos los clientes almacenados")
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Devuelve un libro segun su ID")
    public Cliente getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id);
    }

    @PostMapping()
    @Operation(summary = "Agregar un nuevo cliente",  description = "Crea un nuevo cliente")
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.saveCliente(cliente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un cliente existente", description = "Modifica los datos de un cliente por su ID")
    public Cliente updaCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente existingCliente = clienteService.getClienteById(id);
        if (existingCliente != null) {
            existingCliente.setNombre(cliente.getNombre());
            existingCliente.setRut(cliente.getRut());
            existingCliente.setCorreo(cliente.getCorreo());
            existingCliente.setContrasenia(cliente.getContrasenia());
            existingCliente.setTelefono(cliente.getTelefono());
            existingCliente.setRegion(cliente.getRegion());
            existingCliente.setComuna(cliente.getComuna());
            existingCliente.setFechaNacimiento(cliente.getFechaNacimiento());
            return clienteService.saveCliente(existingCliente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un cliente", description = "Elimina un cliente por su ID")
    public void deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
    }
    
}
