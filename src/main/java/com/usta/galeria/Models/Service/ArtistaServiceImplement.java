package com.usta.galeria.Models.Service;

import com.usta.galeria.Entities.ArtistaEntity;
import com.usta.galeria.Models.DAO.ArtistaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class ArtistaServiceImplement implements ArtistaService {

    @Autowired
    private ArtistaDao artistaDao;

    @Override
    @Transactional
    public List<ArtistaEntity> findAll() {
        List<ArtistaEntity> artistas = artistaDao.findAll();
        artistas.sort(Comparator.comparing(ArtistaEntity::getNombreArt)
                .thenComparing(ArtistaEntity::getApellidoArt));
        return artistas;
    }

    @Override
    @Transactional
    public void save(ArtistaEntity artistas) {
        artistaDao.save(artistas);

    }

    @Override
    @Transactional
    public ArtistaEntity findById(Long id) {

        return artistaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteByID(Long id) {
        artistaDao.deleteById(id);
    }

    @Override
    @Transactional
    public ArtistaEntity actualizarArtistaEntity(ArtistaEntity artistas) {
        return artistaDao.save(artistas);
    }

    @Override
    @Transactional
    public void changeState(Long id) {
        artistaDao.changesState(id);
    }

    @Override
    public ArtistaEntity viewDetail(Long id) {
        return artistaDao.viewDetails(id);

    }

}