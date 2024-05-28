/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dosideas.activosFijos.repository;

import com.dosideas.activosFijos.domain.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author usuario
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    /**
     * Retorna la lista de categorias ordenadas por id
     *
     * @return
     */
    @Query("Select v from Categoria v order by v.idCategoria")
    List<Categoria> buscarCategorias();

    /**
     * Retorna la lista de categorias encontradas por su nombre
     *
     * @return
     */
    @Query("from Categoria v where v.descripcionCat like %?1%")
    List<Categoria> buscar(String consulta);

    /**
     * Retorna la lista de categorias encontradas con mas de tres activos 
     *
     * @return
     */
    @Query("SELECT c FROM Categoria c JOIN c.activosFijos a GROUP BY c HAVING COUNT(a) > 3")
    List<Categoria> findCategoriasConMasDeTresActivos();
    
    /**
     * Manda a llamar al procedimiento almacenado para editar categoria 
     *
     * 
     */
     @Modifying
    @Query(value = "CALL EditarCategoria(:categoriaId, :nuevaDescripcion)", nativeQuery = true)
    void editarCategoria(@Param("categoriaId") int categoriaId, @Param("nuevaDescripcion") String nuevaDescripcion);
}
