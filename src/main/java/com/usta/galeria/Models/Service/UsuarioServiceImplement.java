package com.usta.galeria.Models.Service;


import com.usta.galeria.Entities.UsuarioEntity;
import com.usta.galeria.Models.DAO.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImplement implements UsuarioService{
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public List<UsuarioEntity> findAll() {
        return (List<UsuarioEntity>) usuarioDAO.findAll();
    }

    @Override
    public void save(UsuarioEntity usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    public UsuarioEntity findById(Long id) {
        return usuarioDAO.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        usuarioDAO.deleteById(id);
    }

    @Override
    public UsuarioEntity actualizarUsuarioEntity(UsuarioEntity usuario) {
        return  usuarioDAO.save(usuario);
    }

    @Override
    public void changeState(Long id) {
        usuarioDAO.changeState(id);
    }

    @Override
    public UsuarioEntity findByEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }
}
