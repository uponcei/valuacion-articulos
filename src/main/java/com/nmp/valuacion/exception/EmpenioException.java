package com.nmp.valuacion.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

/**
 * @name EmpenioException
 * @description Clase de excepcion lanzada cuando se genera un error relacionados con el calculo del empenio
 *
 * @author Uriel P. Ibarra
 * @version 0.1
 */

public class EmpenioException extends BaseException {

    @Serial
    private static final long serialVersionUID = -4233312398815067482L;

    public static final EmpenioException CODIGO_NO_ENCONTRADO =
            new EmpenioException("No se encuentro el código del artículo.",
                    "01", HttpStatus.NOT_FOUND);
    public static final EmpenioException FALLO_CALCULAR_EMPENIO =
            new EmpenioException("Error al procesar el cálculo del empeño tradicional.",
                    "02", HttpStatus.INTERNAL_SERVER_ERROR);

    public static final EmpenioException PARAMETRO_INCORRECTO =
            new EmpenioException("Los parametros de la petición no son correctos",
                    "03", HttpStatus.BAD_REQUEST);

    public EmpenioException(String descripcion, String estado, HttpStatus status) {
        super(descripcion, estado, status);
    }

    @Override
    public String toString() {
        return "EmpenioException{" +
                "id='" + id + '\'' +
                ", estado='" + estado + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", status=" + status +
                '}';
    }

}
