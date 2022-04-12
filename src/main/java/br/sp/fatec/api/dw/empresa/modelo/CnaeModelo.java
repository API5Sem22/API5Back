//package br.sp.fatec.api.dw.empresa.modelo;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Data
//@Entity
//@Table(name = "Cnae")
//public class CnaeModelo {
//
//    @Id
//    @Column(name = "cne_id")
//    private Integer id;
//
//    @Column(name = "cne_cod")
//    private String codigo;
//
//    @Column(name = "cne_descricao")
//    private String descricao;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "idCnae", fetch = FetchType.LAZY)
//    private List<EmpresaModelo> empresas;
//}
