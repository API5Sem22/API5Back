package br.sp.fatec.api.dw.usuarios.modelo;

import br.sp.fatec.api.dw.cargo.CargoModel;
import br.sp.fatec.api.dw.carteira.CarteiraModel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Usuario")
public class UsuarioModelo {

    @Id
    @Column(name = "usu_email",length = 80)
    private String email;

    @Column(name = "usu_nome",nullable = false,length = 80)
    private String nome;

    @Column(name = "usu_senha",nullable = false,length = 30)
    private String senha;

    @Column(name = "usu_departamento",length = 80)
    private String departamento;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CargoModel cargo;

    @ManyToOne
    @JoinColumn(name = "crt_id")
    private CarteiraModel carteira;

}
