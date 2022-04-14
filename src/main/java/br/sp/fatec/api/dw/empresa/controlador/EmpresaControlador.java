package br.sp.fatec.api.dw.empresa.controlador;

import br.sp.fatec.api.dw.empresa.modelo.EmpresaModelo;
import br.sp.fatec.api.dw.empresa.servico.EmpresaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/empresas")
@RestController
@CrossOrigin
public class EmpresaControlador {

    private final EmpresaServico service;

    @Autowired
    public EmpresaControlador(EmpresaServico service) {
        this.service = service;
    }

    @GetMapping("/org")
    public ResponseEntity<EmpresaModelo> listaEmpresa(@RequestBody EmpresaModelo modelo){
        EmpresaModelo empresaModelo = service.listaPorEmail(modelo);
        return new ResponseEntity<>(empresaModelo, HttpStatus.OK);
    }

    @PutMapping("/upt")
    public ResponseEntity<Void> atualizar(@RequestBody EmpresaModelo modelo){
        service.atualizar(modelo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
