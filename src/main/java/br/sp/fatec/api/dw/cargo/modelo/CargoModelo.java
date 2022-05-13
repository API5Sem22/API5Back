package br.sp.fatec.api.dw.cargo.modelo;

import br.sp.fatec.api.dw.usuarios.modelo.UsuarioModelo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "Cargo")
public class CargoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Integer idCargo;

    @Column(name = "car_descricao",nullable = false,unique = true,length = 50)
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "cargo", fetch = FetchType.LAZY)
    private Set<UsuarioModelo> usuarios;

}
