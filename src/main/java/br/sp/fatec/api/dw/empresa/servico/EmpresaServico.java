package br.sp.fatec.api.dw.empresa.servico;

import br.sp.fatec.api.dw.empresa.modelo.EmpresaDescModelo;
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

    public EmpresaModelo listaPorEmail(String cnpj){
        EmpresaDescModelo emp = new EmpresaDescModelo();
        emp.setCnpj(cnpj);
        return repository.findByCnpj(emp);
    }

    public void atualizar(EmpresaModelo modelo) {
        try {
            EmpresaModelo empresaModelo = listaPorEmail(modelo.getCnpj().getCnpj());

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
