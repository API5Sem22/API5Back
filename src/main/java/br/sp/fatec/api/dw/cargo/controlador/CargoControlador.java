package br.sp.fatec.api.dw.cargo.controlador;

import br.sp.fatec.api.dw.cargo.servico.CargoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cargo")
public class CargoControlador {

    private final CargoServico servico;

    @Autowired
    public CargoControlador(CargoServico servico) {
        this.servico = servico;
    }


}
