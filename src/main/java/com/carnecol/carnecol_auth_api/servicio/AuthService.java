package com.carnecol.carnecol_auth_api.servicio;

import com.carnecol.carnecol_auth_api.modelo.Usuario;
import com.carnecol.carnecol_auth_api.repositorio.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Contiene la lógica para registrar usuarios e iniciar sesión.
@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder codificador;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.codificador = new BCryptPasswordEncoder();
    }

    // Registra un usuario y protege su contraseña.
    public String registrar(Usuario usuario) {

        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            return "El usuario ya está registrado";
        }

        usuario.setContrasena(codificador.encode(usuario.getContrasena()));
        usuarioRepository.save(usuario);

        return "Usuario registrado correctamente";
    }

    // Comprueba las credenciales para iniciar sesión.
    public String iniciarSesion(Usuario usuario) {

        return usuarioRepository.findByUsuario(usuario.getUsuario())
                .filter(u -> codificador.matches(
                        usuario.getContrasena(), u.getContrasena()))
                .map(u -> "Autenticación satisfactoria")
                .orElse("Error en la autenticación");
    }
}