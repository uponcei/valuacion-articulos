package com.nmp.valuacion.model;


import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Nombre: Precios
 * Descripcion: Entidad que representa el catalogo de lista de precios.
 *
 * @author Uriel P. Ibarra
 * @version 0.1
 */
@Document(collection = "precios")
public class Precios {

    private String codigo;
    private String material;
    private BigDecimal precio;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Precios() {
    }
    public Precios(String codigo, String material, BigDecimal precio) {
        this.codigo = codigo;
        this.material = material;
        this.precio = precio;
    }

    @Override
    public String toString(){
        return "Precio [codigo=" + codigo + ", material=" + material + ", precio=" + precio + "]";
    }
}
