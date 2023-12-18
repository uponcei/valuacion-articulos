package com.nmp.valuacion.controller;

import com.nmp.valuacion.model.dto.MaterialReqDTO;
import com.nmp.valuacion.model.dto.MaterialRespDTO;
import com.nmp.valuacion.exception.EmpenioException;
import com.nmp.valuacion.service.PrecioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Controlador de Empeños", description = "Servicio que nos permite realizar las operaciones relacionadas con los empeños")
public class EmpenioController {

    @Autowired
    private PrecioService precioService;

    @ResponseBody
    @GetMapping(value = "/calcularEmpenio", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation( summary = "Calcula el monto estimado del préstamo con base en el material y peso del artículo", responses = {
            @ApiResponse(responseCode = "200", description = "Calculo de empeño exitoso."),
            @ApiResponse(responseCode = "400", description = "El o los parametros especificados son invalidos."),
            @ApiResponse(responseCode = "401", description = "Fallo de autentificacion, credenciales invalidas."),
            @ApiResponse(responseCode = "404", description = "El recurso que desea no fue encontrado"),
            @ApiResponse(responseCode = "500", description = "Error no esperado") })
    public ResponseEntity<MaterialRespDTO> calcularEmpenio(@RequestBody @Valid @NotNull MaterialReqDTO materialReqDTO) throws EmpenioException {

        if(materialReqDTO.getPeso().compareTo(BigDecimal.ZERO) < 0)
            throw EmpenioException.PARAMETRO_INCORRECTO;

        MaterialRespDTO response = precioService.calcularEmpenio(materialReqDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
