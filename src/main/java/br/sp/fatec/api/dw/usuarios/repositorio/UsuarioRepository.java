package br.sp.fatec.api.dw.usuarios.repositorio;

import br.sp.fatec.api.dw.usuarios.modelo.UsuarioModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModelo, String> {

    UsuarioModelo findByEmail(String email);

    void deleteByEmail(String email);
}
