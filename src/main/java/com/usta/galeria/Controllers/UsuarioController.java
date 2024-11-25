package com.usta.galeria.Controllers;

import com.usta.galeria.Entities.RolEntity;
import com.usta.galeria.Entities.UsuarioEntity;
import com.usta.galeria.Models.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/register")
    public String crearUsuario(Model model) {
        model.addAttribute("usuario", new UsuarioEntity());
        model.addAttribute("title","Registar un Usuario");
        return "register";
    }
    @PostMapping(value = "/register")
    public String register(@ModelAttribute("usuario") @Valid UsuarioEntity usuario,
                           BindingResult result, @RequestParam("confirmarClave") String confirmarClave,
                           Model model, SessionStatus status) {
        if (result.hasErrors()) {
            return "register";
        }
        if (!usuario.getClaveUsuario().equals(confirmarClave)) {
            result.rejectValue("clave","error.usuario",
                    "La contraseña no coincide");
            return "register";
        }
        String pass =  new BCryptPasswordEncoder().encode(usuario.getClaveUsuario());
        usuario.setClaveUsuario(pass);
        usuario.setEstadoUsuario(true);

        RolEntity rolVisitante = new RolEntity();
        rolVisitante.setRol("2L");
        usuario.setIdRol(rolVisitante);
        usuarioService.save(usuario);
        status.setComplete();
        return "redirect:/login";
    }
}