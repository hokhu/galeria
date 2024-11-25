package com.usta.galeria.Models.DAO;
import com.usta.galeria.Entities.ArtistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ArtistaDao extends JpaRepository<ArtistaEntity, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE ArtistaEntity SET estadoArt=false WHERE idArtista=?1")
    public void changesState(Long idArtista);

    @Transactional
    @Query("SELECT artista FROM ArtistaEntity artista WHERE artista.idArtista=?1")
    public ArtistaEntity viewDetails(Long idArtista);

}
