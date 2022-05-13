package br.sp.fatec.api.dw.servico;

import br.sp.fatec.api.dw.cargo.modelo.CargoModelo;
import br.sp.fatec.api.dw.carteira.CarteiraModel;
import br.sp.fatec.api.dw.usuarios.modelo.UsuarioModelo;
import br.sp.fatec.api.dw.usuarios.servico.UsuarioServico;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class UsuarioServicoTest {

    @Autowired
    private UsuarioServico usuarioServico;

    @Test
    void inserirUsuarioOkTest(){
        UsuarioModelo usu = new UsuarioModelo();
        usu.setEmail("bi.henriquesf@hotmail.com");
        usu.setNome("Gabriel");
        usu.setSenha("Senha");
        usu.setDepartamento("RH");

        usuarioServico.registrar(usu);

        assertNotNull(usu.getEmail());
    }

    @Test
    public void ListarEmailUsuarioTestOk() {
        UsuarioModelo usu = new UsuarioModelo();
        usu.setEmail("teste@teste.com.br");
        usu.setNome("Teste");
        usu.setSenha("123");

        usuarioServico.registrar(usu);

        assertNotNull(usuarioServico.listaPorEmail("teste@teste.com.br"));

    }

    @Test
    public void ListarEmailUsuarioTestF() {
        UsuarioModelo usu = new UsuarioModelo();
        usu.setEmail("teste@teste.com.br");
        usu.setNome("Teste");
        usu.setSenha("123");

        usuarioServico.registrar(usu);

        assertNull(usuarioServico.listaPorEmail("bi.henriquesf@hotmail.com"));

    }

    @Test
    public void DeletarUsuarioTestOk() {
        UsuarioModelo usu = new UsuarioModelo();
        usu.setEmail("teste@teste.com.br");
        usu.setNome("Teste");
        usu.setSenha("123");

        usuarioServico.registrar(usu);
        usuarioServico.deletar("teste@teste.com.br");

        assertNull(usuarioServico.listaPorEmail("teste@teste.com.br"));
    }

}
