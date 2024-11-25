package com.usta.galeria.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "OBRA")
public class ObraEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obra")
    private Long idObra;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "titulo_obra", length = 50, nullable = false)
    private String tituloObra;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion_obra", length = 100, nullable = false)
    private String descripcionObra;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tecnica_obra", length = 50)
    private String tecnicaObra;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "foto_obra", length = 200, nullable = false)
    private String fotoObra;

    @NotNull
    @Column(name = "estado_obra", nullable = false)
    private Boolean estadoObra;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "Obras_Artistas",
            joinColumns = @JoinColumn(name = "id_obra", referencedColumnName = "id_obra"),
            inverseJoinColumns = @JoinColumn(name = "id_artista", referencedColumnName = "id_artista")
    )
    private Collection<ArtistaEntity> artistas;

    @Override
    public String toString() {
        return "Obra: " + tituloObra;
    }
}
