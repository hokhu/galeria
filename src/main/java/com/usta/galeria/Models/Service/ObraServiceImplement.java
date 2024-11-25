package com.usta.galeria.Models.Service;


import com.usta.galeria.Entities.ObraEntity;
import com.usta.galeria.Models.DAO.ObraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ObraServiceImplement implements ObraService {

    @Autowired
    private ObraDAO obraDao;

    @Override
    public List<ObraEntity> findAll() {
        List<ObraEntity> obras = obraDao.findAll();
        obras.sort(Comparator.comparing(ObraEntity::getTituloObra));
        return obras;
    }

    @Override
    public void save(ObraEntity obra) {
        obraDao.save(obra);
    }

    @Override
    public ObraEntity findById(Long id) {
        return obraDao.findById(id).orElse(null);
    }

    @Override
    public void deleteByID(Long id) {
        obraDao.deleteById(id);
    }

    @Override
    public ObraEntity actualizarObraEntity(ObraEntity obra) {
        return obraDao.save(obra);
    }

    @Override
    public void changeState(Long id) {
        obraDao.changesState(id);
    }

    @Override
    public ObraEntity viewDetail(Long id) {
        return obraDao.viewDetails(id);
    }

    @Override
    public List<ObraEntity> findByObraNombre(String tituloObra) {

        return obraDao.findByTituloObraContainingIgnoreCase(tituloObra);
    }
}
