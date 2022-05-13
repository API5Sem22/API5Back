package br.sp.fatec.api.dw.empresa.controlador;

import br.sp.fatec.api.dw.empresa.modelo.EmpresaModelo;
import br.sp.fatec.api.dw.empresa.servico.EmpresaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RequestMapping("/empresas")
@RestController
@CrossOrigin
public class EmpresaControlador {

    private final EmpresaServico service;

    @Autowired
    public EmpresaControlador(EmpresaServico service) {
        this.service = service;
    }

    @GetMapping("/org/{cnpj}")
    public ResponseEntity<EmpresaModelo> listaEmpresa(@PathVariable String cnpj){
        EmpresaModelo empresaModelo = service.listaPorCnpj(cnpj);
        return new ResponseEntity<>(empresaModelo, HttpStatus.OK);
    }

    @GetMapping("/carteira/{email}")
    public ResponseEntity<List<EmpresaModelo>> listaPorVendedor(@PathVariable String email){
        List<EmpresaModelo> empresaModelo = service.listaPorVendedor(email);
        return new ResponseEntity<>(empresaModelo, HttpStatus.OK);
    }

    @GetMapping("/livres")
    public ResponseEntity<List<EmpresaModelo>> listaLivres(@RequestParam(value = "size", defaultValue = "10") int size,
                                                           @RequestParam(value = "page", defaultValue = "0") int page){
        Page<EmpresaModelo> empresaModelo = service.listaLivres(size, page);
        return new ResponseEntity<>(empresaModelo.getContent(), HttpStatus.OK);
    }

    @PutMapping("/upt")
    public ResponseEntity<Void> atualizar(@RequestBody EmpresaModelo modelo){
        service.atualizar(modelo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/atualiza-vendedor")
    public ResponseEntity<Void> atualizarVendedor(@RequestBody EmpresaModelo modelo){
        service.atualizar(modelo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/csv")
    public ResponseEntity<Void> listaEmpresasCsv() throws IOException {
        service.populateNivelEmpresa();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
