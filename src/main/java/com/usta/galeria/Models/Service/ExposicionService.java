package com.usta.galeria.Models.Service;


import com.usta.galeria.Entities.ExposicionEntity;


import java.util.List;

public interface ExposicionService {
    public List<ExposicionEntity>findAll();
    public void save(ExposicionEntity exposicion);
    public ExposicionEntity findById(Long id);
    public void deleteById(Long id);
    public ExposicionEntity actualizarExposicionEntity(ExposicionEntity exposicion);
    public void changeState(Long id);
    public ExposicionEntity viewDetail(Long id);

}
