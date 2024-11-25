package com.usta.galeria.Controllers;
import com.usta.galeria.Entities.ArtistaEntity;
import com.usta.galeria.Entities.ObraEntity;
import com.usta.galeria.Models.Service.ArtistaService;
import com.usta.galeria.Models.Service.ObraService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ObraController {
    @Autowired
    private ObraService obraService;

    @Autowired
    private ArtistaService artistaService;

    // Listar obras con búsqueda
    @GetMapping(value = "/obras")
    public String listarObra(Model model, @RequestParam(value = "searchTerm", required = false) String searchTerm) {
        List<ObraEntity> obras;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            obras = obraService.findByObraNombre(searchTerm);
        } else {
            obras = obraService.findAll();
        }
        model.addAttribute("title", "Listado de obras");
        model.addAttribute("urlRegistro", "/crearObra");
        model.addAttribute("obras", obras);
        return "obras/listarObra";
    }



    /*--------------------------------------------------------------------------------------------------------------*/

    @RequestMapping(value = "/detalleObra/{id}")
    public String detalleObra(Model model, @PathVariable(value = "id") long idObra) {
        model.addAttribute("title", "Detalle de obras");
        model.addAttribute("detalleObra", obraService.viewDetail(idObra));
        return "obras/detalleObra";
    }

    /*--------------------------------------------------------------------------------------------------------------*/
    @GetMapping(value = "/crearObra")
    public String crearObra(Model model) {
        model.addAttribute("title", "Crear una Obra");
        model.addAttribute("obra", new ObraEntity());
        List<ArtistaEntity> listaArtista = artistaService.findAll();
        if (listaArtista == null) {
            listaArtista = new ArrayList<>();
        }
        model.addAttribute("listaArtista", listaArtista);
        return "obras/crearObra";
    }

    /*--------------------------------------------------------------------------------------------------------------*/
    @PostMapping(value = "/crearObra")
    public String guardarObra(@Valid ObraEntity obra,
                              @RequestParam(value = "foto") MultipartFile foto,
                              BindingResult result,
                              @RequestParam List<Long> artistas) {
        String nombreImagen = guardarImagen(foto);
        if (result.hasErrors()) {
            return "error500";
        }
        obra.setFotoObra(nombreImagen);
        obra.setEstadoObra(true);
        List<ArtistaEntity> artistasSeleccionados = new ArrayList<>();
        for (Long idAutor : artistas) {
            ArtistaEntity artista = artistaService.findById(idAutor);
            if (artista != null) {
                artistasSeleccionados.add(artista);
            }
        }
        obra.setArtistas(artistasSeleccionados);
        obraService.save(obra);
        return "redirect:/obras";
    }

    private String guardarImagen(MultipartFile imagen) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://api.imgbb.com/1/upload");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("key", "6b2a46b2a4568b45f6f6c712e25a0000", ContentType.TEXT_PLAIN);
            builder.addBinaryBody("image", imagen.getInputStream(),
                    ContentType.DEFAULT_BINARY, imagen.getOriginalFilename());
            HttpEntity multipart = (HttpEntity) builder.build();
            httpPost.setEntity(multipart);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200) {
                String responString = EntityUtils.toString(responseEntity);

                JSONObject jsonResponse = new JSONObject(responString);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    JSONObject data = jsonResponse.getJSONObject("data");
                    String imageUrl = data.getString("url");
                    return imageUrl;
                } else {
                    String errorMessage = jsonResponse.optString("error", "Error desconocido");
                    System.err.println("Error al cargar la imagen" + errorMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*--------------------------------------------------------------------------------------------------------------*/
    @RequestMapping(value = "/eliminarObra/{id}")
    public String eliminarById(@PathVariable(value = "id") Long id) throws IOException {

        if (id > 0) {
            ObraEntity obra = obraService.findById(id);
            if (obra != null) {
                obra.getArtistas().clear();
                obraService.save(obra);
                obraService.deleteByID(id);
            }
        } else {
            return "redirect:/error500";
        }
        return "redirect:/obras";
    }

    /*--------------------------------------------------------------------------------------------------------------*/
    @GetMapping(value = "/editarObra/{id}")
    public String editarObra(@PathVariable(value = "id") long idObra, Model model) {
        ObraEntity obra = obraService.findById(idObra);
        model.addAttribute("title", "Editar Obra");
        model.addAttribute("obraEdit", obra);
        model.addAttribute("imagen", obra.getFotoObra());
        List<ArtistaEntity> listaArtista = artistaService.findAll();
        if (listaArtista == null) {
            listaArtista = new ArrayList<>();
        }
        model.addAttribute("listaArtista", listaArtista);
        return "obras/editarObra";
    }

    /*--------------------------------------------------------------------------------------------------------------*/
    @PostMapping(value = "/editarObra/{id}")
    public String editObra(@ModelAttribute("obraEdit") ObraEntity obraEntity,
                           @PathVariable(value = "id") Long idObra,
                           @RequestParam(value = "foto") MultipartFile foto,
                           @RequestParam(value = "artistas") List<Long> artistasIds,
                           BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "error500";
        }
        ObraEntity obraAuxiliar = obraService.findById(idObra);
        obraAuxiliar.setIdObra(idObra);
        obraAuxiliar.setTituloObra(obraEntity.getTituloObra());
        obraAuxiliar.setDescripcionObra(obraEntity.getDescripcionObra());
        obraAuxiliar.setTecnicaObra(obraEntity.getTecnicaObra());
        obraAuxiliar.setFechaCreacion(obraEntity.getFechaCreacion());
        obraAuxiliar.setEstadoObra(true);

        String imagen = obraAuxiliar.getFotoObra();
        String nombreImagen = guardarImagen(foto);
        if (nombreImagen == null || nombreImagen.isBlank() || nombreImagen.isEmpty()) {
            obraAuxiliar.setFotoObra(imagen);
        } else {
            obraAuxiliar.setFotoObra(nombreImagen);
        }
        obraAuxiliar.getArtistas().clear();
        if (artistasIds != null) {
            for (Long artistaId : artistasIds) {
                ArtistaEntity artista = artistaService.findById(artistaId);
                if (artista != null) {
                    obraAuxiliar.getArtistas().add(artista);
                }
            }
        }
        obraService.actualizarObraEntity(obraAuxiliar);
        return "redirect:/obras";
    }
}
