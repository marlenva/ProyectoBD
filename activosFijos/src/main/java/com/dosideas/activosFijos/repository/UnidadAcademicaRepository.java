/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dosideas.activosFijos.repository;

import com.dosideas.activosFijos.domain.UnidadAcademica;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author usuario
 */
public interface UnidadAcademicaRepository extends JpaRepository<UnidadAcademica, Integer>{
    
}
