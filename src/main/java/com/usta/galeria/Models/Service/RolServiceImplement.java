package com.usta.galeria.Models.Service;


import com.usta.galeria.Entities.RolEntity;
import com.usta.galeria.Models.DAO.RolDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImplement implements RolService {
    @Autowired
    public RolDAO rolDAO;

    @Override
    public List<RolEntity> findAll() {
        return (List<RolEntity>) rolDAO.findAll();
    }

    @Override
    public void save(RolEntity rol) {
        rolDAO.save(rol);
    }

    @Override
    public RolEntity findById(Long id) {
        return rolDAO.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        rolDAO.deleteById(id);
    }

    @Override
    public RolEntity actualizarRolEntity(RolEntity rol) {
        return rolDAO.save(rol);
    }
}
