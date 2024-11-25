package com.usta.galeria.Models.Service;

import com.usta.galeria.Entities.ObraEntity;

import java.util.List;

public interface ObraService {
    public List<ObraEntity> findAll();
    public void save(ObraEntity obra);
    public ObraEntity findById(Long id);
    public void deleteByID(Long id);
    public ObraEntity actualizarObraEntity(ObraEntity obra);
    public void changeState(Long id);
    public ObraEntity viewDetail(Long id);
    public List<ObraEntity> findByObraNombre(String tituloObra) ;
}
