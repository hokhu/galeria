package com.usta.galeria.Controllers;

import com.usta.galeria.Entities.ArtistaEntity;
import com.usta.galeria.Entities.ObraEntity;
import com.usta.galeria.Models.Service.ArtistaService;

import com.usta.galeria.Models.Service.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.List;
import java.util.Set;

@Controller
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;
    @Autowired
    private ObraService obraService;

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


    @RequestMapping(value= "/detalleArtista/{id}")
    public String detalleArtista(Model model, @PathVariable (value = "id")Long id){
        ArtistaEntity artista = artistaService.findById(id);
        Collection<ObraEntity> obras = artista.getObra();
        model.addAttribute("title","Detalle de Artista");
        model.addAttribute("detalleArt", artistaService.viewDetail(id));
        return "artistas/detalleArtista";
    }


    @PostMapping("/editarArtista")
    public String actualizarArtista(@ModelAttribute ArtistaEntity artista) {
        artistaService.save(artista);
        return "redirect:/artistas";
    }

}
