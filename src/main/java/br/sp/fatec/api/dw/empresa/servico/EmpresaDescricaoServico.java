package br.sp.fatec.api.dw.empresa.servico;


import br.sp.fatec.api.dw.empresa.modelo.EmpresaDescModelo;
import br.sp.fatec.api.dw.empresa.repositorio.EmpresaDescricaoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaDescricaoServico {


    private final EmpresaDescricaoRepositorio repositorio;

    @Autowired
    public EmpresaDescricaoServico(EmpresaDescricaoRepositorio repositorio) {
        this.repositorio = repositorio;
    }
    public Optional<EmpresaDescModelo> procuraPorId(String id){
        return repositorio.findById(id);
    }

    public void atualizar(EmpresaDescModelo modeloEmpresa) {
    EmpresaDescModelo empresaModelo = repositorio.findByCnpj(modeloEmpresa.getCnpj());
    if (empresaModelo != null){
        empresaModelo.setEmail(modeloEmpresa.getEmail());
        empresaModelo.setTelefone(modeloEmpresa.getTelefone());

        repositorio.save(empresaModelo);
    }

    }
}
