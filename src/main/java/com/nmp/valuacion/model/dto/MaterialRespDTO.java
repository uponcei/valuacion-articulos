package com.nmp.valuacion.model.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Nombre: MaterialRespDTO
 * Descripcion: Clase que encapsula la informacion perteneciente al calculo de empeño de un articulo
 *
 * @author Uriel P. Ibarra
 * @version 0.1
 */
public class MaterialRespDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5921668724562776284L;

    private String codigo;
    private BigDecimal peso;
    private String descripcion;
    private BigDecimal montoEmpenio;

    public MaterialRespDTO() {
        super();
    }

    public MaterialRespDTO(String codigo, BigDecimal peso, String descripcion, BigDecimal montoEmpenio) {
        this.codigo = codigo;
        this.peso = peso;
        this.descripcion = descripcion;
        this.montoEmpenio = montoEmpenio;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMontoEmpenio() {
        return montoEmpenio;
    }

    public void setMontoEmpenio(BigDecimal montoEmpenio) {
        this.montoEmpenio = montoEmpenio;
    }

    @Override
    public String toString() {
        return "MaterialRespDTO [codigo=" + codigo + ", descripcion=" + descripcion + ", peso=" + peso +
                ", montoEmpenio=" + montoEmpenio + "]";
    }
}
