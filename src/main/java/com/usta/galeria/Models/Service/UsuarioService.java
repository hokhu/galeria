package com.usta.galeria.Models.Service;

import com.usta.galeria.Entities.UsuarioEntity;

import java.util.List;

public interface UsuarioService {
    public List<UsuarioEntity>findAll();
    public void save(UsuarioEntity usuario);
    public UsuarioEntity findById(Long id);
    public void deleteById(Long id);
    public UsuarioEntity actualizarUsuarioEntity(UsuarioEntity usuario);
    public void changeState(Long id);
    public UsuarioEntity findByEmail(String email);
}
