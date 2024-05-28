/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dosideas.activosFijos.controller;

import com.dosideas.activosFijos.domain.Categoria;
import com.dosideas.activosFijos.repository.ActivoFijoRepository;
import com.dosideas.activosFijos.service.CategoriaService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author usuario
 */
@Controller
public class CategoriaController {

    private final CategoriaService categoriaService;
   

    //constructor
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;

    }

    /**
     * Crear una nueva categoria
     *
     * @param model
     * @return
     */
    @RequestMapping("/categorias/crear")
    public String mostrarFormAlta(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "formCategoria.html";
    }

    /**
     * Inserta una nueva categoria
     *
     * @param categoria
     * @return
     */
    @PostMapping("/categorias/guardar")
    public String guardar(Categoria categoria) {
        categoriaService.guardar(categoria);
        return "redirect:/categorias";
    }

    /**
     * Elimina un activo según su Id.
     *
     * @param idCategoria
     * @return
     */
    @RequestMapping("/categorias/eliminar")
    public String eliminarcategoriaPorId(@RequestParam("id") String idCategoria) {
        int idv = Integer.parseInt(idCategoria);
        categoriaService.eliminarPorId(idv);
        return "redirect:/categorias";
    }

    /**
     * Mostrar la pagina de edicion de categorias
     *
     * @param idCategoria
     * @param model
     * @return
     */
    @RequestMapping("/categorias/editar")
    public String mostrarEditarCategoria(@RequestParam("id") String idCategoria, Model model) {
        String mensajeError = "";
        try {
            // Intenta convertir el ID de la categoria a un entero
            int id = Integer.parseInt(idCategoria);

            // Busca la categoria por su ID
            Optional<Categoria> opcionalActivoFijo = categoriaService.buscarPorId(id);

            if (opcionalActivoFijo.isPresent()) {
                // Si la categoria se encuentra, lo agrega al modelo y muestra la vista de la categoria
                Categoria categoria = opcionalActivoFijo.get();
                model.addAttribute("categoria", categoria);
                return "formEditarCategoria.html";
            } else {
                // Si la categoria no se encuentra, muestra una página de error con el mensaje correspondiente
                mensajeError = "Categoria no encontrada";
                model.addAttribute("mensajeError", mensajeError);
                return "error";
            }
        } catch (NumberFormatException e) {
            // Si ocurre una excepción al convertir el ID a entero, muestra una página de error con el mensaje correspondiente
            mensajeError = "Id. de la categoria inválido";
            model.addAttribute("mensajeError", mensajeError);
            return "error";
        }

    }

    /**
     * Guarda cambios al editar una categoria
     *
     * @param categoria
     * @return
     */
     @PostMapping("/categorias/guardarCambios")
    public String guardarCambios(@ModelAttribute("categoria") Categoria categoria) {
        categoriaService.editarCategoria(categoria.getIdCategoria(), categoria.getDescripcionCat());
        return "redirect:/categorias";
    }

    /**
     * Permite realizar búsquedas de categorias por la descripcion
     *
     * @param consulta
     * @param model
     * @return
     */
    @RequestMapping("/buscarCategoria")
    public String buscarCategoria(@RequestParam("q") String consulta, Model model) {
        List<Categoria> categoria = categoriaService.buscar(consulta);
        model.addAttribute("categoria", categoria);
        return "categorias.html";
    }

    /**
     * Permite buscar categorías con más de 3 activos
     *
     * @param model
     * @return
     */
    @RequestMapping("/categorias/masDeTresActivos")
    public String buscarCategoriasConMasDeTresActivos(Model model) {
        List<Categoria> categoria = categoriaService.buscarCategoriasConMasDeTresActivos();
        model.addAttribute("categoria", categoria);
        return "categorias.html";
    }
    
    @PutMapping("/categorias/{categoriaId}")
    public ResponseEntity<Void> editarCategoria(
            @PathVariable int categoriaId, 
            @RequestBody String nuevaDescripcion) {
        categoriaService.editarCategoria(categoriaId, nuevaDescripcion);
        return ResponseEntity.ok().build();
    }
}
