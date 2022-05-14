package br.sp.fatec.api.dw.empresa.repositorio;

import br.sp.fatec.api.dw.empresa.modelo.CnaeModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CnaeRepositorio extends JpaRepository <CnaeModelo,Integer> {

    CnaeModelo findByCodigo(Integer codigo);
}
