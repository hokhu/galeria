package com.usta.galeria.Models.Service;
import com.usta.galeria.Entities.ExposicionEntity;
import com.usta.galeria.Models.DAO.ExposicionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


@Service
public class ExposicionServiceImplement implements ExposicionService {
    @Autowired
    private ExposicionDAO exposicionDao;

    @Override
    public List<ExposicionEntity> findAll() {
        List<ExposicionEntity> exposicion = exposicionDao.findAll();
        exposicion.sort(Comparator.comparing(ExposicionEntity::getTituloExp).reversed());
        return exposicion;
    }

    @Override
    public void save(ExposicionEntity exposicion) {
        exposicionDao.save(exposicion);
    }

    @Override
    public ExposicionEntity findById(Long id) {
        return exposicionDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        exposicionDao.deleteById(id);
    }

    @Override
    public ExposicionEntity actualizarExposicionEntity(ExposicionEntity exposicion) {
        return exposicionDao.save(exposicion);
    }

    @Override
    public void changeState(Long id) {
        exposicionDao.changesState(id);
    }

    @Override
    public ExposicionEntity viewDetail(Long id) {
        return exposicionDao.viewDetails(id);
    }
}
