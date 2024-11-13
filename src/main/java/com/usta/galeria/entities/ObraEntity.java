package com.usta.galeria.entities;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "OBRAS")
public class ObraEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obra")
    private Long idObra;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titulo_obra", length = 100,nullable = false)
    private String tituloObra;

    @NotNull
    @Size(min = 1, max = 250)
    @Column(name ="descripcion_obra", length = 250, nullable = false)
    private String descripcionObra;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "ano_creacion")
    private Date anoCreacion;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name ="tecnica_obra", length = 250, nullable = false)
    private String tecnicaObra;

    @NotNull
    @Size(min = 1 , max = 200)
    @Column(name = "foto_obra", length = 200, nullable = false)
    private String fotoObra;

    @NotNull
    @JoinColumn(name = "id_artista", referencedColumnName = "id_artista")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ArtistaEntity artista;

}