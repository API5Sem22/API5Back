package br.sp.fatec.api.dw.usuarios.servico;

import br.sp.fatec.api.dw.usuarios.modelo.UsuarioModelo;
import br.sp.fatec.api.dw.usuarios.repositorio.UsuarioRepository;
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
        }catch (IllegalArgumentException e){
            e.getStackTrace();
        }
    }

    public void deletar(String email) {
        try {
            repository.deleteByEmail(email);
        } catch (Exception e){
            e.getStackTrace();
        }
    }

    public void atualizar(UsuarioModelo modelo) {
        try {
            UsuarioModelo usuarioModelo = listaPorEmail(modelo.getEmail());

            usuarioModelo.setDepartamento(modelo.getDepartamento());

            repository.save(usuarioModelo);
        }catch (NullPointerException e){
            e.getStackTrace();
        }
    }

    public void atualizarCarteira(UsuarioModelo modelo) {
        UsuarioModelo usuario = new UsuarioModelo();
        usuario = repository.findByEmail(modelo.getEmail());
        if (usuario != null){
            usuario.setCarteira(modelo.getCarteira());
            repository.save(usuario);
        }
    }
}
