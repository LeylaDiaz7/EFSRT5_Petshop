package com.cibertec.login_server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;

    private String username;
    private String password;
    private String nombre;
    private String email;
    private String telefono;
    private String role;
}
