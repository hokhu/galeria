package com.usta.galeria.security;

import com.usta.galeria.Models.DAO.UsuarioDAO;
import com.usta.galeria.Entities.UsuarioEntity;
import com.usta.galeria.Entities.RolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Collection;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioDAO.findByEmail(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("El usuario no existe");
        }

        System.out.println("Comprobando rol: " + usuario.getIdRol());

        return new User(
                usuario.getEmailUsuario(),
                usuario.getClaveUsuario(),
                mapearAutoridadesRol(usuario.getIdRol())
        );
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRol(RolEntity rol) {
        return List.of(new SimpleGrantedAuthority(rol.getRol()));
    }
}
