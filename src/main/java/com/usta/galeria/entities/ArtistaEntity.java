package com.usta.galeria.entities;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "ARTISTA")
public class ArtistaEntity implements Serializable {
    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_artista")
    private Long idArtista;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name ="nombre_artista", length = 50, nullable = false)
    private String nombreArtista;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name ="apellido_artista", length = 50, nullable = false)
    private String apellidoArtista;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name ="nacionalidad_artista", length = 50, nullable = false)
    private String nacionalidadArtista;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @ManyToMany(mappedBy = "artistas")
    private Set<ExposicionEntity> exposicion;

}