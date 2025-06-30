package com.cibertec.login_server.service;

import com.cibertec.login_server.entity.Usuario;
import com.cibertec.login_server.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerClientes() {
        return usuarioRepository.findByRoleIgnoreCase("CLIENT");
    }
}
