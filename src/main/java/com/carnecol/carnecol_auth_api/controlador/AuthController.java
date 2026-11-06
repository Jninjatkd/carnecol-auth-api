package com.carnecol.carnecol_auth_api.controlador;

import com.carnecol.carnecol_auth_api.modelo.Usuario;
import com.carnecol.carnecol_auth_api.servicio.AuthService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

// Controlador que recibe las solicitudes de autenticación.
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Recibe los datos para registrar un usuario.
    @PostMapping("/registro")
    public Map<String, String> registrar(@RequestBody Usuario usuario) {
        String mensaje = authService.registrar(usuario);
        return Map.of("mensaje", mensaje);
    }

    // Recibe los datos para iniciar sesión.
    @PostMapping("/login")
    public Map<String, String> iniciarSesion(@RequestBody Usuario usuario) {
        String mensaje = authService.iniciarSesion(usuario);
        return Map.of("mensaje", mensaje);
    }
}