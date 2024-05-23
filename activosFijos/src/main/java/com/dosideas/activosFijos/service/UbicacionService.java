/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dosideas.activosFijos.service;

import com.dosideas.activosFijos.domain.Ubicacion;
import com.dosideas.activosFijos.repository.UbicacionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class UbicacionService {
    //creamos una variable de tipo UbicacionRepository
    private final UbicacionRepository ubicacionRepository;

    //constructor
    public UbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }
    
    /**
     * Retorna una lista de ubicacones para mostrarla en un combo
     * @return 
     */
    public List<Ubicacion> buscarTodos(){
        return ubicacionRepository.findAll();
    }
}
