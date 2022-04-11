package br.sp.fatec.api.dw.cargo.repositorio;

import br.sp.fatec.api.dw.cargo.modelo.CargoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepositorio extends JpaRepository<CargoModelo, Integer> {
}
