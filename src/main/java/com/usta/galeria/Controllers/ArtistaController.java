package com.usta.galeria.Controllers;

import com.usta.galeria.Entities.ArtistaEntity;
import com.usta.galeria.Models.Service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @GetMapping("/artistas")
    public String listarArtistas(Model model) {
        model.addAttribute("title", "Listado de artistas");
        model.addAttribute("urlRegistro", "/crearArtista");
        List<ArtistaEntity> artistas = artistaService.findAll();
        model.addAttribute("artistas", artistas);
        return "artistas/listarArtista";
    }


    @GetMapping("/crearArtista")
    public String crearArtista(Model model) {
        model.addAttribute("title", "Crear Artista");
        model.addAttribute("artista", new ArtistaEntity());
        return "artistas/crearArtista";
    }


    @PostMapping("/crearArtista")
    public String guardarArtista(@ModelAttribute ArtistaEntity artista) {
        artistaService.save(artista);
        return "redirect:/artistas";
    }


    @GetMapping("/editarArtista/{id}")
    public String editarArtista(@PathVariable("id") Long id, Model model) {
        ArtistaEntity artista = artistaService.findById(id);
        if (artista != null) {
            model.addAttribute("title", "Editar Artista");
            model.addAttribute("artista", artista);
            return "artistas/editarArtista";

        }else{
            return "redirect:/artistas";
        }
    }


    @PostMapping("/editarArtista")
    public String actualizarArtista(@ModelAttribute ArtistaEntity artista) {
        artistaService.save(artista);
        return "redirect:/artistas";
    }

}
