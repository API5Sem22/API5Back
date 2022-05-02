package br.sp.fatec.api.dw.empresa.repositorio;

import br.sp.fatec.api.dw.empresa.modelo.CidadeModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepositorio extends JpaRepository <CidadeModelo,Integer> {

    Optional<CidadeModelo> findById(Integer id);
}
