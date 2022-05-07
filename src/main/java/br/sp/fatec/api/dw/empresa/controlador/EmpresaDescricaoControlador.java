package br.sp.fatec.api.dw.empresa.controlador;

import br.sp.fatec.api.dw.empresa.modelo.EmpresaDescModelo;
import br.sp.fatec.api.dw.empresa.servico.EmpresaDescricaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa-descricao")
public class EmpresaDescricaoControlador {

    private final EmpresaDescricaoServico servico;

    @Autowired
    public EmpresaDescricaoControlador(EmpresaDescricaoServico servico) {
        this.servico = servico;
    }

    @PutMapping("/atualiza-descricao-empresa")
    public ResponseEntity<Void> atualizaEmpresa(@RequestBody EmpresaDescModelo modelo){
        servico.atualizar(modelo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
