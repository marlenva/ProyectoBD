/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dosideas.activosFijos.controller;

import com.dosideas.activosFijos.domain.ActivoFijo;
import com.dosideas.activosFijos.domain.Categoria;
import com.dosideas.activosFijos.domain.Ubicacion;
import com.dosideas.activosFijos.domain.UnidadAcademica;
import com.dosideas.activosFijos.service.ActivoFijoService;
import com.dosideas.activosFijos.service.CategoriaService;
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
public class ListaController {

    private final ActivoFijoService activoService;
    private final CategoriaService categoriaService;
    private final UnidadAcademicaService unidadService;
    
    //constructor
    public ListaController(ActivoFijoService activoService, CategoriaService categoriaService, 
             UnidadAcademicaService unidadService) {
        this.activoService = activoService;
        this.categoriaService = categoriaService;
        this.unidadService = unidadService;
    }

    /*@RequestMapping("/")
    public String listarActivos(Model model) {
        List<ActivoFijo> destacados = activoService.buscarActivos();

        model.addAttribute("activos", destacados);

        return "lista.html";
    }*/
    //Lista los activos de 8 en 8 
    @RequestMapping("/lista")
    public String listarActivos(Model model, @RequestParam(defaultValue = "0") int pagina) {
        int tamActivos = 8; // Número de activos por página
        List<ActivoFijo> destacados = activoService.buscarActivos();

        // Calcular el número total de páginas
        int totalPaginas = (int) Math.ceil((double) destacados.size() / tamActivos);

        // Asegurarse de que la página solicitada no sea mayor que el total de páginas
        if (pagina >= totalPaginas) {
            pagina = totalPaginas - 1;
        }

        // Calcular el índice del primer y último producto en la página actual
        int inicioIndice = pagina * tamActivos;
        int finalIndice = Math.min(inicioIndice + tamActivos, destacados.size());

        // Obtener los activos para la página actual
        List<ActivoFijo> activosPaginados = destacados.subList(inicioIndice, finalIndice);
         List<Categoria> cat = categoriaService.buscarCategorias();
         List<UnidadAcademica> uni = unidadService.buscarTodos();

         model.addAttribute("unidades", uni);
        model.addAttribute("activos", activosPaginados);
         model.addAttribute("categorias", cat);
        model.addAttribute("totalPaginas", totalPaginas);
        model.addAttribute("currentPage", pagina);

        return "lista.html";
    }

    //Muestra la lista de categorias en su respectiva pagina 
    @RequestMapping("/categorias")
    public String listarCategorias(Model model) {
        List<Categoria> destacados = categoriaService.buscarCategorias();

        model.addAttribute("categoria", destacados);

        return "categorias.html";
    }
    

     //Muestra la lista de categorias en su respectiva pagina 
    @RequestMapping("/subconsultas")
    public String listar(Model model) {
       List<ActivoFijo> destacados = activoService.buscarActivos();

        model.addAttribute("activos", destacados);

        return "subconsultas.html";
    }

    /**
     * Retorna una lista de elementos según la categoria
     *
     * @param categoriaId
     * @param model
     * @return
     */
    @RequestMapping("/activosfijosPorCategoria")
    public String listarActivosPorCategoria(int categoriaId, Model model) {
        List<ActivoFijo> activoFijo = activoService.buscarPorCategoria(categoriaId);
        model.addAttribute("activos", activoFijo);
        return "lista.html";
    }
    
    @RequestMapping("/activosfijosPorUnidad")
    public String listarActivosPorUnidad(int unidadId, Model model) {
        List<ActivoFijo> activoFijo = activoService.buscarPorUnidad(unidadId);
        model.addAttribute("activos", activoFijo);
        return "lista.html";
    }

    /**
     * Permite realizar búsquedas de activos fijos por la descripcion
     *
     * @param consulta
     * @param model
     * @return
     */
    @RequestMapping("/buscar")
    public String buscar(@RequestParam("q") String consulta, Model model) {
        List<ActivoFijo> activoFijo = activoService.buscar(consulta);
        List<Categoria> categoria = categoriaService.buscar(consulta);
        model.addAttribute("activos", activoFijo);
        model.addAttribute("categoria", categoria);
        model.addAttribute("consulta", consulta);
        return "lista.html";
    }

    /**
     * Maneja la solicitud para buscar un activo fijo por su ID.
     *
     * @param idActivo El ID del activo a buscar.
     * @param model El modelo para agregar atributos para la vista.
     * @return La vista que mostrará los detalles del activo fijo o una página
     * de error.
     */
    @RequestMapping("/buscarActivoFijoPorId")
    public String buscarVideojuegoPorId(@RequestParam("activoId") String idActivo, Model model) {
        String mensajeError = "";

        try {
            // Intenta convertir el ID del activo fijo a un entero
            int id = Integer.parseInt(idActivo);

            // Busca el activo fijo por su ID
            Optional<ActivoFijo> opcionalActivo = activoService.buscarPorId(id);

            if (opcionalActivo.isPresent()) {
                // Si el videojuego se encuentra, lo agrega al modelo y muestra la vista del activo fijo
                ActivoFijo activofijo = opcionalActivo.get();
                model.addAttribute("activo", activofijo);
                return "activo.html";
            } else {
                // Si el activo no se encuentra, muestra una página de error con el mensaje correspondiente
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
    @RequestMapping("/categoria")
    public String getByCategoria(@RequestParam("descripcionCat") String consulta, Model model) {
        List<ActivoFijo> activos = activoService.getActivosFijosByCategoria(consulta);
        model.addAttribute("activos", activos);
        model.addAttribute("consulta", consulta);
        return "lista.html";
    }

    @RequestMapping("/ubicacion")
    public String getByUbicacion(@RequestParam("edificio") String consulta, Model model) {
        List<ActivoFijo> activos = activoService.getActivosFijosByUbicacion(consulta);
        model.addAttribute("activos", activos);
        model.addAttribute("consulta", consulta);
        return "lista.html";
    }

    @RequestMapping("/unidadacademica")
    public String getByUnidadAcademica(@RequestParam("nombre") String consulta, Model model) {
        List<ActivoFijo> activos = activoService.getActivosFijosByUnidadAcademica(consulta);
        model.addAttribute("activos", activos);
        model.addAttribute("consulta", consulta);
        return "lista.html";
    }      
}
