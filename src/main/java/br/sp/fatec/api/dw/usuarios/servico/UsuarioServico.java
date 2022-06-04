package br.sp.fatec.api.dw.usuarios.servico;

import br.sp.fatec.api.dw.usuarios.modelo.UsuarioModelo;
import br.sp.fatec.api.dw.usuarios.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.security.MessageDigest;
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

    public UsuarioModelo login(UsuarioModelo modelo){
        UsuarioModelo usu = repository.findByEmailAndSenha(modelo.getEmail(), getSHA512(modelo.getSenha()));
        if(usu != null) {
            usu.setSenha(null);
        }
        return usu;
    }

    public void registrar(UsuarioModelo modelo) {
        try {
            modelo.setSenha(getSHA512(modelo.getSenha()));
            repository.save(modelo);
        } catch (DataIntegrityViolationException di){
            throw new DataIntegrityViolationException("Restrição de banco de dados violada",di);
        } catch (NullPointerException np){
            throw new NullPointerException("Elemento não encontrado");
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


    public static String getSHA512(String input){
        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }
}
