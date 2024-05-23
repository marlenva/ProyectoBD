/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dosideas.activosFijos.service;

import com.dosideas.activosFijos.domain.ActivoFijo;
import com.dosideas.activosFijos.repository.ActivoFijoRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class ActivoFijoService {
    
    //creamos una variable de tipo ActivoFijoReporitory
    private final ActivoFijoRepository activoRepository;
    
    //constructor
    public ActivoFijoService(ActivoFijoRepository activoRepository) {
        this.activoRepository = activoRepository;
    }
    
     /**
     * Retorna una lista de activos fijos 
     * @return 
     */
    public List<ActivoFijo> buscarActivos() {
        return activoRepository.buscarActivos();
    }
    
     /**
     * Retorna una lista de activos fijos por id. de la categoria 
     * @param categoriaId
     * @return 
     */
    public List<ActivoFijo> buscarPorCategoria(int categoriaId){
        return activoRepository.buscarPorCategoria(categoriaId);
    }
    
     /**
     * Retorna una lista de activos fijos por id. de la categoria 
     * @param unidadId
     * @return 
     */
    public List<ActivoFijo> buscarPorUnidad(int unidadId){
        return activoRepository.buscarPorUnidad(unidadId);
    }
    
    /**
     * Retorna una consulta a la bd para buscar
     * un activo fijo por su descripcion
     * @param consulta
     * @return
     */
    public List<ActivoFijo> buscar(String consulta){
        return activoRepository.buscar(consulta);
    }
    
    /**
     * Guardar un activo fijo en la bd
     * @param activoFijo
     * @return 
     */
    public ActivoFijo guardar(ActivoFijo activoFijo){
        return activoRepository.save(activoFijo);
    }
    
    /**
     * Permite buscar un activo fijo por su identificador
     * @param id
     * @return 
     */
    public Optional<ActivoFijo> buscarPorId(int id){
        return activoRepository.findById(id);        
    }
    
    /**
     * Elimina un activo por su id
     * @param id 
     */
    public void eliminarPorId(int id){
        activoRepository.deleteById(id);
    }
    
     /**
     *Guardar los cambios del activo
     * @param activoFijo
     * 
     */
    public void guardarCambios(ActivoFijo activoFijo){
       activoRepository.save(activoFijo);
    }
     /**
     * Obtiene los activos fijos por el nombre de la categoria 
     * @param consulta
     * @return 
     */
    public List<ActivoFijo> getActivosFijosByCategoria(String consulta) {
        return activoRepository.buscarPorNomCategoria(consulta);
    }
     /**
     * Obtiene los activos fijos por el edificio al que pertenece
     * @param consulta
     * @return 
     */
    public List<ActivoFijo> getActivosFijosByUbicacion(String consulta) {
        return activoRepository.buscarPorEdificio(consulta);
    }

     /**
     * Obtiene los activos fijos por el nombre de la unidad academica
     * @param consulta
     * @return   
     */
    public List<ActivoFijo> getActivosFijosByUnidadAcademica(String consulta) {
        return activoRepository.buscarPorNomUnidad(consulta);
    }
}
