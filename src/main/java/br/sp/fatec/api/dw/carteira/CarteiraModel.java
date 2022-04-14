package br.sp.fatec.api.dw.carteira;

import br.sp.fatec.api.dw.usuarios.modelo.UsuarioModelo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Carteira")
public class CarteiraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crt_id")
    private Integer idCarteira;

    @Column(name = "crt_descricao",nullable = false,unique = true,length = 50)
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "carteira", fetch = FetchType.LAZY)
    private List<UsuarioModelo> usuarios;
}
