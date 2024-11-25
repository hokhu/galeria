package com.usta.galeria.Controllers;

import com.usta.galeria.Entities.ArtistaEntity;
import com.usta.galeria.Entities.ExposicionEntity;
import com.usta.galeria.Models.Service.ArtistaService;
import com.usta.galeria.Models.Service.ExposicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExposicionController {

    @Autowired
    private ExposicionService exposicionService;

    @Autowired
    private ArtistaService artistaService;

    @GetMapping("/exposiciones")
    public String listarExposiciones(Model model) {
        model.addAttribute("title", "Listado de exposiciones");
        model.addAttribute("urlRegistro", "/crearExposicion");
        model.addAttribute("exposiciones", exposicionService.findAll());
        return "exposiciones/listarExposiciones";

    }

    @RequestMapping(value = "/detalleExposicion/{id}")
    public String detalleExposicion(Model model, @PathVariable(value = "id") long idExposicion) {
        model.addAttribute("title", "Detalle de la Exposicion");
        model.addAttribute("detalleE",exposicionService.viewDetail(idExposicion));
        return "exposiciones/detalleExposicion";
    }

    @GetMapping("/crearExposicion")
    public String crearExposicion(Model model) {
        model.addAttribute("title", "Crear exposición");
        model.addAttribute("exposicion", new ExposicionEntity());
        model.addAttribute("listaArtista", artistaService.findAll());
        return "exposiciones/crearExposiciones";
    }

    @PostMapping("/crearExposicion")
    public String guardarExposicion(@Valid ExposicionEntity exposicion, BindingResult result,
                                    @RequestParam List<Long> artistas) {
        if (result.hasErrors()) {
            return "exposiciones/crearExposiciones";
        }
        List<ArtistaEntity> artistasSeleccionados = new ArrayList<>();
        for (Long idAutor : artistas) {
            ArtistaEntity artista = artistaService.findById(idAutor);
            if (artista != null) {
                artistasSeleccionados.add(artista);
            }
        }
        exposicion.setArtistas(artistasSeleccionados);
        exposicionService.save(exposicion);
        return "redirect:/exposiciones";
    }

    @GetMapping (value = "/editarExposicion/{id}")
    public String editarExposicion(@PathVariable(value = "id") long idExposicion, Model model) {
        ExposicionEntity exposicion = exposicionService.findById(idExposicion);
        model.addAttribute("title", "Editar Exposición");
        model.addAttribute("expoEdit", exposicion);
        List<ArtistaEntity> listaArtista = artistaService.findAll();
        if (listaArtista == null) {
            listaArtista = new ArrayList<>();
        }
        model.addAttribute("listaArtista", listaArtista);
        return "exposiciones/editarExposicion";
    }

    @PostMapping(value = "/editarExposicion/{id}")
    public String editExpo(@ModelAttribute("expoEdit") ExposicionEntity exposicionEntity,
                           @PathVariable(value = "id") Long idExposicion,
                           @RequestParam(value = "artistas") List<Long> artistasIds,
                           BindingResult result)throws IOException {
        if (result.hasErrors()) {
            return "error500";
        }
        ExposicionEntity ExposicionAuxiliar = exposicionService.findById(idExposicion);
        ExposicionAuxiliar.setIdExposicion(idExposicion);
        ExposicionAuxiliar.setTituloExp(exposicionEntity.getTituloExp());
        ExposicionAuxiliar.setFechaInicio(exposicionEntity.getFechaInicio());
        ExposicionAuxiliar.setFechaFin(exposicionEntity.getFechaFin());
        ExposicionAuxiliar.setUbicacionExp(exposicionEntity.getUbicacionExp());
        ExposicionAuxiliar.setEstadoExposicion(true);

        ExposicionAuxiliar.getArtistas().clear();
        if (artistasIds !=null){
            for (Long artistaId : artistasIds) {
                ArtistaEntity artista = artistaService.findById(artistaId);
                if (artista != null) {
                    ExposicionAuxiliar.getArtistas().add(artista);
                }
            }
        }
        exposicionService.actualizarExposicionEntity(ExposicionAuxiliar);
        return "redirect:/exposiciones";
    }

}
