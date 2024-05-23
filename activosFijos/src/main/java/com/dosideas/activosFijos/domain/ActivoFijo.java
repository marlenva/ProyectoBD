/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dosideas.activosFijos.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

/**
 *
 * @author usuario
 */
@Entity
public class ActivoFijo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cuaf;   
    private String descripcion;
    private String condicion;
    @ManyToOne
    @JoinColumn(name = "id_unidad")
    private UnidadAcademica unidad;
    @ManyToOne
    @JoinColumn(name = "id_ubicacion")
    private Ubicacion ubicacion;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    private String numSerie;
    private LocalDate fechaAlta;
    private String observaciones;
    private String url;

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the act_condiciones
     */
    public String getCondicion() {
        return condicion;
    }

    /**
     * @param condicion the act_condiciones to set
     */
    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    /**
     * @return the numSerie
     */
    public String getNumSerie() {
        return numSerie;
    }

    /**
     * @param numSerie the numSerie to set
     */
    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    /**
     * @return the fechaAlta
    */ 
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    /**
     * @param fechaAlta the fechaAlta to set
    */
    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    } 

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the cuaf
    */ 
    public int getCuaf() {
        return cuaf;
    }

    /**
     * @param cuaf the cuaf to set
    */ 
    public void setCuaf(int cuaf) {
        this.cuaf = cuaf;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the unidad
     */
    public UnidadAcademica getUnidad() {
        return unidad;
    }

    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(UnidadAcademica unidad) {
        this.unidad = unidad;
    }

    /**
     * @return the ubicacion
     */
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
    