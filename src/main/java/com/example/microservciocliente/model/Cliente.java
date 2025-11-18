package com.example.microservciocliente.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = false, nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String rut;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(unique = false, nullable = false)
    private String contrasenia;

    @Column(unique = false, nullable = true)
    private String telefono;

    @Column(unique = false, nullable = false)
    private Date fechaNacimiento;

    @Column(unique = false, nullable = false)
    private String region;

    @Column(unique = false, nullable = false)
    private String comuna;
}
