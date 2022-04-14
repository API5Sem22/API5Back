package br.sp.fatec.api.dw.empresa.modelo;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "Empresa_Descricao")
public class EmpresaDescModelo {

    @Id
    @Column(name = "emp_cnpj_d")
    private String cnpj;

    @Column(name = "emp_nome_d")
    private String nome;

    @Column(name = "emp_porte_d")
    private String porte;

    @Column(name = "emp_tipo_d")
    private String tipo;

    @Column(name = "emp_situacao_d")
    private String situacao;

    @Column(name = "emp_data_abertura_d")
    private String dataAbertura;

    @Column(name = "emp_email_d")
    private String email;

    @Column(name = "emp_telefone_d")
    private String telefone;

    @Column(name = "emp_natureza_juridica_d")
    private String naturezaJuridica;

}
