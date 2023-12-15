package com.nmp.valuacion.exception;

import org.springframework.http.HttpStatus;

/**
 * @name SistemaException
 * @description Clase de excepcion lanzada cuando se genera un error de sistema
 *
 * @author Uriel P. Ibarra
 * @version 0.1
 */
public class SistemaException extends BaseException {

    private static final long serialVersionUID = 7560984206887757294L;

    public static final SistemaException INTERNAL_SERVER_ERROR =
            new SistemaException("Ocurrio un error interno en el servidor",
                    "00", HttpStatus.INTERNAL_SERVER_ERROR);

    public static final SistemaException ERROR_DESCONOCIDO =
            new SistemaException("Error desconocido. Por favor, notifique al administrador", "00",
                    HttpStatus.INTERNAL_SERVER_ERROR);

    public SistemaException(String descripcion, String estado, HttpStatus status) {
        super(descripcion, estado, status);
    }
}
