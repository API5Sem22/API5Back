package br.sp.fatec.api.dw.empresa.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Cidade")
public class CidadeModelo {

    @Id
    @Column(name = "cid_id")
    private Integer id;

    @Column(name = "cid_nome")
    private String nome;

    @Column(name = "cid_estado")
    private String estado;

    @Column(name = "cid_regiao")
    private String regiao;

    @JsonIgnore
    @OneToMany(mappedBy = "idCidade", fetch = FetchType.LAZY)
    private List<EmpresaModelo> empresas;
}
