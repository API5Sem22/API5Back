package br.sp.fatec.api.dw.cargo.modelo;

import br.sp.fatec.api.dw.usuarios.modelo.UsuarioModelo;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Cargo")
public class CargoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id ")
    private Integer idCargo;

    @Column(name = "car_descricao",nullable = false,unique = true,length = 50)
    private String descricao;

    @OneToMany(mappedBy = "cargo", fetch = FetchType.LAZY)
    private List<UsuarioModelo> usuarios;

}