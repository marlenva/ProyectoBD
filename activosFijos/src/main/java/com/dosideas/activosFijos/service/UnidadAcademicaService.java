/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dosideas.activosFijos.service;

import com.dosideas.activosFijos.domain.UnidadAcademica;
import com.dosideas.activosFijos.repository.UnidadAcademicaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class UnidadAcademicaService {
    //creamos una variable de tipo UnidadAcademicaRepository
    private final UnidadAcademicaRepository unidadRepository;

    //constructor
    public UnidadAcademicaService(UnidadAcademicaRepository unidadRepository) {
        this.unidadRepository = unidadRepository;
    }
     /**
     * Retorna una lista de unidades academicas para mostrarla en un combo
     * @return 
     */
    public List<UnidadAcademica> buscarTodos(){
        return unidadRepository.findAll();
    }
    
   
}
