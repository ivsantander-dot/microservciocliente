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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Cliente", description = "Endpoints de cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/admin/getUsuarios")
    @Operation(summary = "Obtiene todos los usuarios", description = "Requiere rol ADMIN")
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteService.listarClientes();
        if (clientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Crea usuario", description = "No requiere rol")
    @PostMapping("/crearUsuarios")
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente){
        Cliente clienteNuevo = clienteService.guardar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteNuevo);
    }

    @Operation(summary = "Obtiene usuarios por id", description = "Requiere rol ADMIN")
    @GetMapping("/admin/getUsuariosId/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Integer id){
        try {
            Cliente cliente = clienteService.encontrarClienteId(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    
    @PutMapping("/admin/actualizarUsuario/{id}")
    @Operation(summary = "Actualiza usuarios", description = "Requiere rol ADMIN")
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
            cli.setRole(cliente.getRole());

            clienteService.guardar(cli);
            return ResponseEntity.ok(cliente);
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/eliminarUsuario/{id}")
    @Operation(summary = "Elimina usuarios", description = "Requiere rol ADMIN")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            clienteService.borrar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
