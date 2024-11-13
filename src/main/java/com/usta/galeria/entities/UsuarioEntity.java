package com.usta.galeria.entities;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "USUARIOS")
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombre_usuario", length = 40,nullable = false)
    private String nombreUsuario;


    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email",unique = true,length = 100,nullable = false)
    private String email;

    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "contraseña",length = 12, nullable = false)
    private String contrasena;

    @NotNull
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RolEntity rol;

}