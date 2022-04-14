package br.sp.fatec.api.dw.empresa.servico;

import br.sp.fatec.api.dw.empresa.modelo.EmpresaModelo;
import br.sp.fatec.api.dw.empresa.repositorio.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServico {

    private final EmpresaRepositorio repository;

    @Autowired
    public EmpresaServico(EmpresaRepositorio repository) {
        this.repository = repository;
    }

    public EmpresaModelo listaPorEmail(EmpresaModelo modelo){
        return repository.findByCnpj(modelo.getCnpj());
    }

    public void atualizar(EmpresaModelo modelo) {
        try {
            EmpresaModelo empresaModelo = listaPorEmail(modelo);

            if(modelo.getNivel() != null) {
                empresaModelo.setNivel(modelo.getNivel());
            }
            if(modelo.getVendedor() != null) {
                empresaModelo.setNivel(modelo.getNivel());
            }

            repository.save(empresaModelo);
        }catch (NullPointerException e){
            e.getStackTrace();
        }
    }

}
