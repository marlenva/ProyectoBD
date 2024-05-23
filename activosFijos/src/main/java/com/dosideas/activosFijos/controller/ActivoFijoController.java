/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dosideas.activosFijos.controller;

import com.dosideas.activosFijos.domain.ActivoFijo;
import com.dosideas.activosFijos.service.ActivoFijoService;
import com.dosideas.activosFijos.service.CategoriaService;
import com.dosideas.activosFijos.service.UbicacionService;
import com.dosideas.activosFijos.service.UnidadAcademicaService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author usuario
 */
@Controller
public class ActivoFijoController {

    private final UnidadAcademicaService unidadService;
    private final UbicacionService ubicacionService;
    private final CategoriaService categoriaService;
    private final ActivoFijoService activoService;

    //constructor
    public ActivoFijoController(UnidadAcademicaService unidadService, UbicacionService ubicacionService,
            CategoriaService categoriaService, ActivoFijoService activoService) {
        this.unidadService = unidadService;
        this.ubicacionService = ubicacionService;
        this.categoriaService = categoriaService;
        this.activoService = activoService;
    }

    /**
     * Crear un nuevo activo fijo
     *
     * @param model
     * @return
     */
    @RequestMapping("/activosfijos/crear")
    public String mostrarFormAlta(Model model) {
        model.addAttribute("unidades", unidadService.buscarTodos());
        model.addAttribute("ubicaciones", ubicacionService.buscarTodos());
        model.addAttribute("categorias", categoriaService.buscarTodos());
        model.addAttribute("activofijo", new ActivoFijo());
        return "formactivofijo.html";
    }

    /**
     * Inserta un nuevo activo fijo
     *
     * @param activoFijo
     * @return
     */
    @PostMapping("/activosfijos/guardar")
    public String guardar(ActivoFijo activoFijo) {
        activoService.guardar(activoFijo);
        return "redirect:/lista";
    }

    /**
     * Elimina un activo según su Id.
     *
     * @param idActivo
     * @return
     */
    @RequestMapping("/activosfijos/eliminar")
    public String eliminarActivoFijoPorId(@RequestParam("id") String idActivo) {
        int idv = Integer.parseInt(idActivo);
        activoService.eliminarPorId(idv);
        return "redirect:/lista";
    }

    /**
     * Mostrar la pagina de edicion de activos fijos
     *
     * @param idActivoFijo
     * @param model
     * @return
     */
    @RequestMapping("/activosfijos/editar")
    public String mostrarEditarActivo(@RequestParam("id") String idActivoFijo, Model model) {
        String mensajeError = "";
        try {
            // Intenta convertir el ID del activo a un entero
            int id = Integer.parseInt(idActivoFijo);

            // Busca el activo por su ID
            Optional<ActivoFijo> opcionalActivoFijo = activoService.buscarPorId(id);

            if (opcionalActivoFijo.isPresent()) {
                // Si el activo fijo se encuentra, lo agrega al modelo y muestra la vista del activo
                ActivoFijo activo = opcionalActivoFijo.get();
                model.addAttribute("activo", activo);
                model.addAttribute("unidades", unidadService.buscarTodos());
                model.addAttribute("ubicaciones", ubicacionService.buscarTodos());
                // Aquí puedes hacer cualquier procesamiento adicional necesario antes de mostrar la página
                model.addAttribute("categorias", categoriaService.buscarTodos());
                return "formEditarActivoFijo.html";
            } else {
                // Si el activo fijo no se encuentra, muestra una página de error con el mensaje correspondiente
                mensajeError = "Activo Fijo no encontrado";
                model.addAttribute("mensajeError", mensajeError);
                return "error";
            }
        } catch (NumberFormatException e) {
            // Si ocurre una excepción al convertir el ID a entero, muestra una página de error con el mensaje correspondiente
            mensajeError = "Id. del activo fijo inválido";
            model.addAttribute("mensajeError", mensajeError);
            return "error";
        }

    }

    /**
     * Guarda cambios al editar un activo
     *
     * @param activoFijo
     * @return
     */
    @PostMapping("/activosfijos/guardarCambios")
    public String guardarCambios(ActivoFijo activoFijo) {
        activoService.guardarCambios(activoFijo);
        return "redirect:/lista";
    }

    

}
