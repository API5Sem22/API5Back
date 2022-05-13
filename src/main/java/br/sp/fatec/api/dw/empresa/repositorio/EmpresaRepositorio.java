package br.sp.fatec.api.dw.empresa.repositorio;

import br.sp.fatec.api.dw.empresa.modelo.EmpresaDescModelo;
import br.sp.fatec.api.dw.empresa.modelo.EmpresaModelo;
import br.sp.fatec.api.dw.usuarios.modelo.UsuarioModelo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepositorio extends JpaRepository<EmpresaModelo, EmpresaDescModelo> {

    EmpresaModelo findByCnpj(EmpresaDescModelo modelo);

    List<EmpresaModelo> findByVendedor(UsuarioModelo vendedor);

    Page<EmpresaModelo> findAllByOrigem(String livre, Pageable pageable);
}
