package com.usta.galeria.Entities;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "EXPOSICION")
public class ExposicionEntity implements Serializable {
    private static  long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_exposicion")
    private Long idExposicion;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "titulo_Exp",length = 50,nullable = false, unique = true)
    private String tituloExp;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaInicio", nullable = false)
    private Date fechaInicio;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaFin")
    private Date fechaFin;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ubicacion_Exp",length = 50,nullable = false, unique = true)
    private String ubicacionExp;

    @NotNull
    @Column(name = "estado_exposicion",columnDefinition = "boolean")
    private Boolean estadoExposicion;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "Exposiciones_Artistas",joinColumns =  @JoinColumn(name = "id_exposicion",
            referencedColumnName = "id_exposicion"),
            inverseJoinColumns = @JoinColumn (name = "id_artista",referencedColumnName = "id_artista"))
    private Collection<ArtistaEntity> artistas;

    @Override
    public String toString() {
        return "Exposicion" + " " + tituloExp;
    }


}
