package com.carnecol.carnecol_auth_api.repositorio;

import com.carnecol.carnecol_auth_api.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Permite consultar y administrar los usuarios en la base de datos.
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsuario(String usuario);
}