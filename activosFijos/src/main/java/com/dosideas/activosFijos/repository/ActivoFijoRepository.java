/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dosideas.activosFijos.repository;

import com.dosideas.activosFijos.domain.ActivoFijo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author usuario
 */
public interface ActivoFijoRepository extends JpaRepository<ActivoFijo, Integer> {

    /**
     * Retorna la lista de activos fijos ordenados por nombre
     *
     * @return
     */
    @Query("Select v from ActivoFijo v order by v.descripcion")
    List<ActivoFijo> buscarActivos();

    /**
     * Retorna la lista de activos fijos ordenados por nombre dependiendo del ID
     * de la categoria seleccionada
     *
     * @return
     */
    @Query("from ActivoFijo v where v.categoria.id = ?1 order by v.descripcion")
    List<ActivoFijo> buscarPorCategoria(int categoriaId);

    /**
     * Retorna la lista de activos fijos ordenados por nombre dependiendo del ID
     * de la unidad academica seleccionada
     *
     * @return
     */
    @Query("from ActivoFijo v where v.unidad.id = ?1 order by v.descripcion")
    List<ActivoFijo> buscarPorUnidad(int unidadId);

    /**
     * •	Retorna la lista de activos fijos ordenados por nombre dependiendo del nombre de la categoría
     *
     * @return
     */
    @Query("SELECT a FROM ActivoFijo a WHERE a.categoria.idCategoria = (SELECT c.idCategoria FROM Categoria c WHERE c.descripcionCat LIKE %:consulta%)")
    List<ActivoFijo> buscarPorNomCategoria(@Param("consulta") String consulta);

    /**
     * •	Retorna la lista de activos fijos ordenados por nombre dependiendo del nombre de la unidad académica 
     *
     * @return
     */
    @Query("SELECT a FROM ActivoFijo a WHERE a.unidad.idUnidad = (SELECT ua.idUnidad FROM UnidadAcademica ua WHERE ua.nombre LIKE %:consulta%)")
    List<ActivoFijo> buscarPorNomUnidad(@Param("consulta") String consulta);

    /**
     * •	Retorna la lista de activos fijos ordenados por nombre dependiendo del edificio en el que se encuentra
     *
     * @return
     */
    @Query("SELECT a FROM ActivoFijo a WHERE a.ubicacion.idUbicacion IN (SELECT u.idUbicacion FROM Ubicacion u WHERE u.edificio LIKE %:consulta%)")
    List<ActivoFijo> buscarPorEdificio(@Param("consulta") String consulta);

    /**
     * Retorna la lista de activos fijos encontrados por su descripcion
     *
     * @return
     */
    @Query("from ActivoFijo v where v.descripcion like %?1%")
    List<ActivoFijo> buscar(String consulta);

}
