package br.sp.fatec.api.dw.usuarios.repositorio;

import br.sp.fatec.api.dw.usuarios.modelo.UsuarioModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModelo, Long> {

    UsuarioModelo findByEmail(String email);
}
