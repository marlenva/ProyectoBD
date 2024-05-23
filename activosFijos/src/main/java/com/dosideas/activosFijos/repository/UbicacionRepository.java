/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dosideas.activosFijos.repository;

import com.dosideas.activosFijos.domain.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author usuario
 */
public interface UbicacionRepository extends JpaRepository<Ubicacion, Integer>{
    
}
