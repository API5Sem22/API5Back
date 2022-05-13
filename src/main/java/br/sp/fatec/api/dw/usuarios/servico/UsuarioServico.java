package br.sp.fatec.api.dw.usuarios.servico;

import br.sp.fatec.api.dw.usuarios.modelo.UsuarioModelo;
import br.sp.fatec.api.dw.usuarios.repositorio.UsuarioRepository;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import javax.servlet.ServletException;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServico {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioServico(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<UsuarioModelo> listaUsuarios() {
        return repository.findAll();
    }

    public UsuarioModelo listaPorEmail(String email){
        return repository.findByEmail(email);
    }

    public void registrar(UsuarioModelo modelo) {
        try {
            repository.save(modelo);
        } catch (DataIntegrityViolationException di){
            throw new DataIntegrityViolationException("Restrição de banco de dados violada",di);
        }
    }

    public void deletar(String email) {
        try {
            repository.deleteById(email);
        } catch (EmptyResultDataAccessException ep){
            throw new EmptyResultDataAccessException("Nenhum elemento encontrado",1);
        }
    }

    public void atualizar(UsuarioModelo modelo) {
        try {
            UsuarioModelo usuarioModelo = listaPorEmail(modelo.getEmail());

            usuarioModelo.setDepartamento(modelo.getDepartamento());

            repository.save(usuarioModelo);
        } catch (NullPointerException nl){
            throw new NullPointerException("Elemento não encontrado");
        } catch (DataIntegrityViolationException di) {
            throw new DataIntegrityViolationException("Restrição de banco de dados violada",di);
        }
    }

    public void atualizarCarteira(UsuarioModelo modelo) {
        try {
            UsuarioModelo usuario = repository.findByEmail(modelo.getEmail());
            if (usuario != null){
                usuario.setCarteira(modelo.getCarteira());
                repository.save(usuario);
            }else {
                throw new NullPointerException("Elemento não encontrado");
            }
        } catch (DataIntegrityViolationException di){
            throw new DataIntegrityViolationException("Restrição de banco de dados violada",di);
        }
    }
}
