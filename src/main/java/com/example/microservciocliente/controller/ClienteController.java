package com.example.microservciocliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservciocliente.model.Cliente;
import com.example.microservciocliente.services.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteService.listarClientes();
        if (clientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente){
        Cliente clienteNuevo = clienteService.guardar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Integer id){
        try {
            Cliente cliente = clienteService.encontrarClienteId(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Integer id, @RequestBody Cliente cliente){
        try {
            Cliente cli = clienteService.encontrarClienteId(id);
            cli.setId(id);
            cli.setNombre(cliente.getNombre());
            cli.setRut(cliente.getRut());
            cli.setCorreo(cliente.getCorreo());
            cli.setContrasenia(cliente.getContrasenia());
            cli.setTelefono(cliente.getTelefono());
            cli.setFechaNacimiento(cliente.getFechaNacimiento());
            cli.setRegion(cliente.getRegion());
            cli.setComuna(cliente.getComuna());

            clienteService.guardar(cli);
            return ResponseEntity.ok(cliente);
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            clienteService.borrar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
