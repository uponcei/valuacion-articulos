package com.nmp.valuacion.service;

import com.nmp.valuacion.ValuacionApplication;
import com.nmp.valuacion.dao.PrecioRepository;
import com.nmp.valuacion.exception.EmpenioException;
import com.nmp.valuacion.model.Precios;
import com.nmp.valuacion.model.dto.MaterialReqDTO;
import com.nmp.valuacion.model.dto.MaterialRespDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ValuacionApplication.class)
public class PrecioServiceTest {

    @Autowired
    PrecioService precioService;

    @MockBean
    PrecioRepository precioRepository;
    private final String codigo = "001";
    @Test
    void whenCalcularEmpenioThenAssertTrue() throws Exception {

        when(this.precioRepository.findByCodigo(anyString())).thenReturn(generarPrecioDTO());

        MaterialRespDTO  materialRespDTO = precioService.calcularEmpenio(generarMaterialReq(BigDecimal.ONE));

        Assertions.assertNotNull(materialRespDTO);
        Assertions.assertEquals(materialRespDTO.getCodigo(), codigo);
    }

    @Test
    void whenCalcularEmpenioThenNotFound() throws Exception {
        try {
            when(this.precioRepository.findByCodigo(anyString())).thenReturn(null);
            MaterialRespDTO materialRespDTO = precioService.calcularEmpenio(generarMaterialReq(BigDecimal.ONE));
        }catch (EmpenioException e){
            Assertions.assertEquals(EmpenioException.CODIGO_NO_ENCONTRADO.getStatus(), e.getStatus());
        }
    }

    @Test
    void whenCalcularEmpenioThenThrowException() throws Exception {
        try {
            when(this.precioRepository.findByCodigo(anyString())).thenReturn(generarPrecioDTO());
            MaterialRespDTO materialRespDTO = precioService.calcularEmpenio(generarMaterialReq(null));
        }catch (EmpenioException e){
            Assertions.assertEquals(EmpenioException.FALLO_CALCULAR_EMPENIO.getStatus(), e.getStatus());
        }
    }

    private Precios generarPrecioDTO (){
        Precios precio = new Precios();
        precio.setCodigo(codigo);
        precio.setMaterial("Oro puro 24k");
        precio.setPrecio(new BigDecimal("1400.00"));
        return precio;
    }

    private MaterialReqDTO generarMaterialReq(BigDecimal peso){
        return new MaterialReqDTO(codigo, peso);
    }

}
