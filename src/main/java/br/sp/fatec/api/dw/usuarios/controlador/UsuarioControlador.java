package br.sp.fatec.api.dw.usuarios.controlador;

import br.sp.fatec.api.dw.usuarios.modelo.UsuarioModelo;
import br.sp.fatec.api.dw.usuarios.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/usuarios")
@RestController
public class UsuarioControlador {


    private final UsuarioServico service;

    @Autowired
    public UsuarioControlador(UsuarioServico service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<UsuarioModelo> listaUsuarios(){
        List<UsuarioModelo> users = service.listaUsuarios();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> registraUsuario(@RequestBody UsuarioModelo modelo){
        service.registrar(modelo);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaUsuario(@RequestParam Long id){
        service.deletar(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> atualizaUsuario(@RequestBody UsuarioModelo modelo){
        service.atualizar(modelo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "nivel-carteira")
    public ResponseEntity<Void> atualizarNivelCarteira(@RequestBody UsuarioModelo modelo){

        service.atualizarCarteira(modelo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
