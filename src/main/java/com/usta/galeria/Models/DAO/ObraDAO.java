package com.usta.galeria.Models.DAO;

import com.usta.galeria.Entities.ObraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObraDAO extends JpaRepository<ObraEntity, Long> {

    // Método de consulta derivada
    List<ObraEntity> findByTituloObraContainingIgnoreCase(String tituloObra);

    // Método de consulta personalizada (opcional)
    @Query("SELECT o FROM ObraEntity o WHERE LOWER(o.tituloObra) LIKE LOWER(CONCAT('%', :tituloObra, '%'))")
    List<ObraEntity> searchByTituloObra(String tituloObra);

    // Cambiar el estado de una obras
    @Query("UPDATE ObraEntity o SET o.estadoObra = NOT o.estadoObra WHERE o.idObra = :id")
    void changesState(Long id);

    // Consultar los detalles de una obras
    @Query("SELECT o FROM ObraEntity o WHERE o.idObra = :id")
    ObraEntity viewDetails(Long id);
}
