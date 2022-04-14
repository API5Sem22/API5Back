package br.sp.fatec.api.dw.cargo.servico;

import br.sp.fatec.api.dw.cargo.repositorio.CargoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoServico {

    private final CargoRepositorio repositorio;
    @Autowired
    public CargoServico(CargoRepositorio repositorio) {
        this.repositorio = repositorio;
    }
}
