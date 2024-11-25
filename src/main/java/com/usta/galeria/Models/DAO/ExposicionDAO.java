package com.usta.galeria.Models.DAO;

import com.usta.galeria.Entities.ExposicionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ExposicionDAO extends JpaRepository<ExposicionEntity, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE ExposicionEntity  SET estadoExposicion=false WHERE idExposicion=?1")
    public void changesState(Long idExposicion);

    @Transactional
    @Query("SELECT exposicion FROM ExposicionEntity exposicion WHERE exposicion.idExposicion=?1")
    public ExposicionEntity viewDetails(Long idExposicion);

}
