package br.sp.fatec.api.dw.empresa.repositorio;

import br.sp.fatec.api.dw.empresa.modelo.EmpresaDescModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaDescricaoRepositorio extends JpaRepository<EmpresaDescModelo, String> {


    EmpresaDescModelo findByCnpj(String cnpj);
}
