package com.nmp.valuacion.service.impl;

import com.nmp.valuacion.dao.PrecioRepository;
import com.nmp.valuacion.model.dto.MaterialReqDTO;
import com.nmp.valuacion.model.dto.MaterialRespDTO;
import com.nmp.valuacion.exception.EmpenioException;
import com.nmp.valuacion.model.Precios;
import com.nmp.valuacion.service.PrecioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @name PrecioServiceImpl
 * @description Clase de capa de servicio que nos ayuda a realizar operaciones de logica de negocios
 *          para un empeño tradicional
 *
 * @author Uriel P. Ibarra
 * @version 0.1
 */
@Service("precioService")
public class PrecioServiceImpl implements PrecioService {

    @Autowired
    private PrecioRepository precioRepository;

    @Value("${valuacion.porcentaje}")
    private BigDecimal porcentaje;


    /**
     * {@inheritDoc}
     */
    @Override
    public MaterialRespDTO calcularEmpenio(MaterialReqDTO materialReqDTO) throws EmpenioException {
        Precios precio = precioRepository.findByCodigo(materialReqDTO.getCodigo());
        if(null == precio) {
            throw  EmpenioException.CODIGO_NO_ENCONTRADO;
        }

       try{
           // obtenemos el monto total de empeño con la siguiente formula Monto_Préstamo = (gramos * PRECIO_GRAMO) * 80%
           BigDecimal total =  (materialReqDTO.getPeso().multiply( precio.getPrecio())).multiply(porcentaje).setScale(2, RoundingMode.HALF_UP);
           return new MaterialRespDTO(materialReqDTO.getCodigo(), materialReqDTO.getPeso(),
                   precio.getMaterial(), total);
       }catch (Exception e){
           throw  EmpenioException.FALLO_CALCULAR_EMPENIO;
       }
    }
}
