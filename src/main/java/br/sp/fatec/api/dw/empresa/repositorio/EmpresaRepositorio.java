package br.sp.fatec.api.dw.empresa.repositorio;

import br.sp.fatec.api.dw.empresa.modelo.CnaeModelo;
import br.sp.fatec.api.dw.empresa.modelo.EmpresaDescModelo;
import br.sp.fatec.api.dw.empresa.modelo.EmpresaModelo;
import br.sp.fatec.api.dw.usuarios.modelo.UsuarioModelo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepositorio extends JpaRepository<EmpresaModelo, EmpresaDescModelo> {

    EmpresaModelo findByCnpj(EmpresaDescModelo modelo);

    List<EmpresaModelo> findByVendedor(UsuarioModelo vendedor);

//    Page<EmpresaModelo> findAllByOrigem(String livre, Pageable pageable);

    List<EmpresaModelo> findAllByOrigemAndProspeccaoAndVendedor(String livre, String prospeccao, UsuarioModelo vendedor);

//    Page<EmpresaModelo> findAllByOrigemAndIdCnae(String livre, CnaeModelo cnaeModelo, Pageable pageable);

    List<EmpresaModelo> findAllByOrigemAndProspeccaoAndVendedorAndIdCnae(String livre, String prospeccao, UsuarioModelo vendedor, CnaeModelo cnaeModelo);

    List<EmpresaModelo> findByVendedorAndIdCnae(UsuarioModelo vendedor, CnaeModelo cnaeModelo);

    List<EmpresaModelo> findByOrigem(String origem);

    List<EmpresaModelo> findByOrigemAndIdCnae(String origem, CnaeModelo cnaeModelo);

    @Query(value = " select TOP 5 c.cne_cod as CodigoCnae, SUM(cm.csm_quantidade_consumo) as Consumo from Cnae c, Empresa_Descricao ed, Empresa e, Consumo cm " +
            "where cm.emp_cnpj_d = ed.emp_cnpj_d and ed.emp_cnpj_d = e.emp_cnpj_d and e.cne_id = c.cne_id " +
            "group by c.cne_cod order by SUM(cm.csm_quantidade_consumo) DESC; ", nativeQuery = true)
    List<String> findConsumosCnae();

    @Query(value = " select TOP 5 c.cid_estado as Estado, SUM(cm.csm_quantidade_consumo) as Consumo from Cidade c, Empresa_Descricao ed, Empresa e, Consumo cm " +
            "where cm.emp_cnpj_d = ed.emp_cnpj_d and ed.emp_cnpj_d = e.emp_cnpj_d and e.cid_id = c.cid_id " +
            "group by c.cid_estado order by SUM(cm.csm_quantidade_consumo) DESC; ", nativeQuery = true)
    List<String> findConsumosEstados();
}
