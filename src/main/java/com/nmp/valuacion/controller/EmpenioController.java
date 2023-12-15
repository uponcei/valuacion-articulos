package com.nmp.valuacion.controller;

import com.nmp.valuacion.model.dto.MaterialReqDTO;
import com.nmp.valuacion.model.dto.MaterialRespDTO;
import com.nmp.valuacion.exception.EmpenioException;
import com.nmp.valuacion.service.PrecioService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


/**
 * @name EmpenioController
 * @description Clase de tipo Controlador que expone los servicios
 *              relacionados para el calculo de un empeño tradicional
 *
 * @author Uriel P. Ibarra
 * @version 0.1
 */
@RestController
@RequestMapping("/valuacion")
public class EmpenioController {

    @Autowired
    private PrecioService precioService;

    @ResponseBody
    @GetMapping(value = "/calcularEmpenio", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MaterialRespDTO> calcularEmpenio(@RequestBody @Valid @NotNull MaterialReqDTO materialReqDTO) throws EmpenioException {

        if(materialReqDTO.getPeso().compareTo(BigDecimal.ZERO) < 0)
            throw EmpenioException.PARAMETRO_INCORRECTO;

        MaterialRespDTO response = precioService.calcularEmpenio(materialReqDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
