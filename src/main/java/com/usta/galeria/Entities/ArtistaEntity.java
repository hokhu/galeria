package com.usta.galeria.Entities;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "ARTISTAS")
public class ArtistaEntity implements Serializable {

    private static final long serialVersionUID = 1L; // Asegúrate de que sea final y correcto

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Artista")
    private Long idArtista;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombre_art", length = 40, nullable = false)
    private String nombreArt;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "apellido_art", length = 40, nullable = false)
    private String apellidoArt;

    @Size(min = 1, max = 50)
    @Column(name = "pais_origen_art", length = 50)
    private String paisOrigenArt;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaNac")
    private Date fechaNac;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "diciplina_art", length = 40, nullable = false)
    private String diciplina_art ;

    @NotNull
    @Column(name = "estado_art", columnDefinition = "boolean", nullable = false)
    private Boolean estadoArt;

    @ManyToMany(mappedBy = "artistas")
    private Set<ObraEntity> obra;

    @ManyToMany(mappedBy = "artistas")
    private Set<ExposicionEntity> exposicion;

    @Override
    public String toString() {
        return nombreArt + " " + apellidoArt;
    }
}
