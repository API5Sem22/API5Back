package br.sp.fatec.api.dw.empresa.modelo;

import br.sp.fatec.api.dw.usuarios.modelo.UsuarioModelo;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Empresa")
public class EmpresaModelo implements Serializable {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "emp_cnpj_d")
    private EmpresaDescModelo cnpjd;

    @ManyToOne
    @JoinColumn(name = "cid_id")
    private CidadeModelo idCidade;

    @ManyToOne
    @JoinColumn(name = "cne_id")
    private CnaeModelo idCnae;

    @Column(name = "emp_origem")
    private String origem;

    @Column(name = "emp_nivel")
    private String nivel;

    @ManyToOne
    @JoinColumn(name = "usu_email")
    private UsuarioModelo vendedor;
}
