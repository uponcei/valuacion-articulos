package com.nmp.valuacion.service;

import com.nmp.valuacion.model.dto.MaterialReqDTO;
import com.nmp.valuacion.model.dto.MaterialRespDTO;
import com.nmp.valuacion.exception.EmpenioException;

/**
 * Nombre: PrecioService
 * Descripcion: Interface que define las operacione encargada de obtener y procesar un empeño tradicional
 *
 * @author Uriel P. Ibarra
 * @version 0.1
 */
public interface PrecioService {
    /**
     * Método que nos ayuda a procesar un empeño tradicional
     * @param materialReqDTO dto con la información del codigo del material y el peso en gramos
     * @return MaterialRespDTO
     * @throws EmpenioException
     */
    public MaterialRespDTO calcularEmpenio(MaterialReqDTO materialReqDTO) throws EmpenioException;
}
