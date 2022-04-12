package br.sp.fatec.api.dw.empresa.controlador;

import br.sp.fatec.api.dw.empresa.modelo.EmpresaDescModelo;
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

    @GetMapping("/{cnpj}")
    public ResponseEntity<EmpresaModelo> listaUsuario(@PathVariable String cnpj){
        EmpresaDescModelo desc = new EmpresaDescModelo();
        desc.setCnpjd(cnpj);
        EmpresaModelo empresaModelo = service.listaPorEmail(desc);
        return new ResponseEntity<>(empresaModelo, HttpStatus.OK);
    }

//    @PutMapping()
//    public ResponseEntity<Void> atualizar(@RequestBody EmpresaModelo modelo){
//        EmpresaDescModelo desc = new EmpresaDescModelo();
//        desc.setCnpjd(cnpj);
//        service.atualizar(desc, emailVendedor, nivel);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
