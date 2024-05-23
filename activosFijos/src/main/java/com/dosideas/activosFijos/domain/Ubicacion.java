/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dosideas.activosFijos.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
/**
 *
 * @author usuario
 */
@Entity
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUbicacion;
    private String edificio;
    private String salon;

    /**
     * @return the idUbicacion
     */
    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    /**
     * @param idUbicacion the idUbicacion to set
     */
    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    /**
     * @return the edificio
     */
    public String getEdificio() {
        return edificio;
    }

    /**
     * @param edificio the edificio to set
     */
    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    /**
     * @return the salon
     */
    public String getSalon() {
        return salon;
    }

    /**
     * @param salon the salon to set
     */
    public void setSalon(String salon) {
        this.salon = salon;
    }
}
