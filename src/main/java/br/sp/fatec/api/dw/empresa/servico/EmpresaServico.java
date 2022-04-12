//package br.sp.fatec.api.dw.empresa.servico;
//
//import br.sp.fatec.api.dw.empresa.modelo.EmpresaDescModelo;
//import br.sp.fatec.api.dw.empresa.modelo.EmpresaModelo;
//import br.sp.fatec.api.dw.empresa.repositorio.EmpresaRepositorio;
//import br.sp.fatec.api.dw.usuarios.modelo.UsuarioModelo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmpresaServico {
//
//    private final EmpresaRepositorio repository;
//
//    @Autowired
//    public EmpresaServico(EmpresaRepositorio repository) {
//        this.repository = repository;
//    }
//
//    public EmpresaModelo listaPorEmail(EmpresaDescModelo modelo){
//
//        return repository.findByCnpjd(modelo);
//    }
//
//    public void atualizar(EmpresaDescModelo modelo, String emailVendedor, String nivel) {
//        try {
//            EmpresaModelo empresaModelo = listaPorEmail(modelo);
//
//            UsuarioModelo usu = new UsuarioModelo();
//            usu.setEmail(emailVendedor);
//
//            empresaModelo.setNivel(nivel);
//            empresaModelo.setVendedor(usu);
//
//            repository.save(empresaModelo);
//        }catch (NullPointerException e){
//            e.getStackTrace();
//        }
//    }
//
//}
