package com.usta.galeria.Models.Service;

import com.usta.galeria.Entities.ArtistaEntity;
import java.util.List;

public interface ArtistaService {

    public List<ArtistaEntity> findAll();
    public void save(ArtistaEntity artistas);
    public ArtistaEntity findById(Long id);
    public void deleteByID(Long id);
    public ArtistaEntity actualizarArtistaEntity(ArtistaEntity artistas);
    public void changeState(Long id);
    public ArtistaEntity viewDetail(Long id);
}

