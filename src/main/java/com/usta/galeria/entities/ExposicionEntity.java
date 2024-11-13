package com.usta.galeria.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

@Data
@Entity
@Table(name = "EXPOSICIONES")
public class ExposicionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exposiciones")
    private Long idExposiciones;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titulo_exposicion", length = 100, nullable = false)
    private String tituloExposicion;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion_exposicion" ,length = 250, nullable = false)
    private String descripcionExposicion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio")
    private Date fechaInicio;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_finalizacion")
    private Date fechaFinalizacion;

    @NotNull
    @Size(min = 1,max = 100)
    @Column(name = "ubicacion", length = 100, nullable = false)
    private String ubicacion;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "exposicion_artista", joinColumns = @JoinColumn(name = "id_exposiciones",
            referencedColumnName ="id_exposiciones"), inverseJoinColumns = @JoinColumn(name = "id_artista",
            referencedColumnName = "id_artista"))
    private Collection<ArtistaEntity> artistas;

}