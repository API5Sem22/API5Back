package br.sp.fatec.api.dw.usuarios.controlador.converter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HellouWordController {

    @GetMapping
    public ResponseEntity<String> hellouword(){

        System.out.println("Ola");
        return new ResponseEntity<>("Ola", HttpStatus.OK);
    }
}
