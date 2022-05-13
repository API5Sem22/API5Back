package br.sp.fatec.api.dw.servico;

import br.sp.fatec.api.dw.cargo.modelo.CargoModelo;
import br.sp.fatec.api.dw.carteira.CarteiraModel;
import br.sp.fatec.api.dw.usuarios.modelo.UsuarioModelo;
import br.sp.fatec.api.dw.usuarios.servico.UsuarioServico;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@Rollback
@SpringBootTest
public class UsuarioServicoTest {

    @Autowired
    private UsuarioServico usuarioServico;
}
