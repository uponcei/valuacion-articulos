package com.nmp.valuacion.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @name MaterialReqDTO
 * @description Clase que encapsula el request con la información para
 *              el calculo del empeño de material
 *
 * @author Uriel P. Ibarra
 * @version 0.1
 */
public class MaterialReqDTO implements Serializable {

    private static final long serialVersionUID = -189592874861035548L;
    @Valid
    @NotNull(message = "El código del artículo es requerido")
    private String codigo;
    @Valid
    @NotNull(message = "El peso del artículo es requerido")
    private BigDecimal peso;

    public MaterialReqDTO() {
        super();
    }
    public MaterialReqDTO(String codigo, BigDecimal peso) {
        this.codigo = codigo;
        this.peso = peso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "MaterialReqDTO [codigo=" + codigo + ", peso=" + peso + "]";
    }
}
