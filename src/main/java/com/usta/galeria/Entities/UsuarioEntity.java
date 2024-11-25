package com.usta.galeria.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "USUARIO" )
public class UsuarioEntity implements Serializable {
    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_usuario")
    private Long idUsuario;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombre_usu",length = 40, nullable = false)
    private String nombreUsuario;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "apellido_usu",length = 40, nullable = false)
    private String apellidoUsuario;


    @Size(min = 1, max = 15)
    @Column(name = "pais",length = 15, nullable = false)
    private String paisUsuario;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email_usu",length = 100, nullable = false, unique = true)
    private String emailUsuario;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "clave_usu",length = 100)
    private String claveUsuario;

    @NotNull
    @Column(name = "estado_usuario",columnDefinition = "boolean",nullable = false)
    private Boolean estadoUsuario;

    @NotNull
    @JoinColumn(name = "id_Rol", referencedColumnName = "id_Rol")
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RolEntity idRol;

}
