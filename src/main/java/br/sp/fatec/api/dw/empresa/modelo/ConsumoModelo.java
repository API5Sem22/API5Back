package br.sp.fatec.api.dw.empresa.modelo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Consumo")
public class ConsumoModelo {

    @Id
    @Column(name = "csm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "emp_cnpj_d")
    private EmpresaDescModelo cnpj;

    @Column(name = "csm_mes_referencia")
    private String mes_referencia;

    @Column(name = "csm_id_produto")
    private String id_produto;

    @Column(name = "csm_quantidade_consumo ")
    private Integer consumo;

    }

