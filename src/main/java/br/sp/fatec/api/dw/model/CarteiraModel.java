package br.sp.fatec.api.dw.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
/*MANY TO ONE USUARIO*/

@Data
@Entity
@Table(name = "Carteira")
public class CarteiraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crt_id  ")
    private Integer idCarteira;

    @Column(name = "crt_descricao",nullable = false,unique = true,length = 50)
    private String descricao;

    @OneToMany(mappedBy = "carteira", fetch = FetchType.LAZY)
    private List<UsuarioModel> usuarios;
}
