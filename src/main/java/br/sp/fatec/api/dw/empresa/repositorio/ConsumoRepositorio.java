package br.sp.fatec.api.dw.empresa.repositorio;

import br.sp.fatec.api.dw.empresa.modelo.ConsumoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumoRepositorio extends JpaRepository <ConsumoModelo,Integer> {
}
