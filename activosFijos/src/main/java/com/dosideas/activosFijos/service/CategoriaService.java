/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dosideas.activosFijos.service;

import com.dosideas.activosFijos.domain.Categoria;
import com.dosideas.activosFijos.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class CategoriaService {

    //creamos una variable de tipo CategoriaRepository
    private final CategoriaRepository categoriaRepository;

    //constructor
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Retorna una lista de categorias para mostrarla en un combo
     *
     * @return
     */
    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

    /**
     * Retorna una lista de categorias
     *
     * @return
     */
    public List<Categoria> buscarCategorias() {
        return categoriaRepository.buscarCategorias();
    }

    /**
     * Elimina una categoria por su id
     *
     * @param id
     */
    public void eliminarPorId(int id) {
        categoriaRepository.deleteById(id);
    }

    /**
     * Permite buscar una categoria por su identificador
     *
     * @param id
     * @return
     */
    public Optional<Categoria> buscarPorId(int id) {
        return categoriaRepository.findById(id);
    }

    /**
     * Guardar los cambios de la categoria
     *
     * @param categoria
     *
     */
    public void guardarCambios(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    /**
     * Guardar una categoria en la bd
     *
     * @param categoria
     * @return
     */
    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    /**
     * Retorna una consulta a la bd para buscar una categoria por su descripcion
     *
     * @param consulta
     * @return
     */
    public List<Categoria> buscar(String consulta) {
        return categoriaRepository.buscar(consulta);
    }
    /**
     * Retorna una lista de categorias con mas de tres activos 
     *
     * 
     * @return
     */
    public List<Categoria> buscarCategoriasConMasDeTresActivos() {
        return categoriaRepository.findCategoriasConMasDeTresActivos();
    }
}
