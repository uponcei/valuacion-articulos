package com.nmp.valuacion.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nmp.valuacion.ValuacionApplication;
import com.nmp.valuacion.model.dto.MaterialReqDTO;
import com.nmp.valuacion.model.dto.MaterialRespDTO;
import com.nmp.valuacion.service.PrecioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

/**
 * Prueba los endpoints definidos en {@link com.nmp.valuacion.controller.EmpenioController}
 *
 * @author Uriel P. Ibarra
 * @version 0.1
 */
@SpringBootTest(classes = ValuacionApplication.class)
@AutoConfigureMockMvc(addFilters = false)
public class EmpenioControllerTest {

    private final String ENDPOINT_BASE = "/valuacion";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PrecioService precioService;

    @Autowired
    private ObjectMapper objectMapper;

    private final String codigo = "001";

    @Test
    void whenCalculaEmpenioReturnOk() throws Exception{
        MaterialReqDTO material = new MaterialReqDTO("001", BigDecimal.ONE);
        when(precioService.calcularEmpenio(any(MaterialReqDTO.class))).thenReturn(generarEmpenioDTO());
        this.mockMvc.perform(get(ENDPOINT_BASE +"/calcularEmpenio")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(material)))
                .andExpect(status().isOk())
                .andExpect((m) -> {
                    MaterialRespDTO materialResult = objectMapper.readValue(m.getResponse().getContentAsString(), MaterialRespDTO.class);
                    Assertions.assertNotNull(materialResult);
                    Assertions.assertEquals(materialResult.getCodigo(),codigo);
                });
    }

    @Test
    void whenPesoIsNegativeReturnException() throws Exception{
        MaterialReqDTO material = new MaterialReqDTO(codigo, new BigDecimal("-1"));
        this.mockMvc.perform(get(ENDPOINT_BASE +"/calcularEmpenio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(material)))
                .andExpect(status().isBadRequest());
    }

    private MaterialRespDTO generarEmpenioDTO (){
        MaterialRespDTO material = new MaterialRespDTO();
        material.setCodigo(codigo);
        material.setDescripcion("Oro puro 24k");
        material.setPeso(BigDecimal.ONE);
        material.setMontoEmpenio(new BigDecimal("1200.00"));
        return material;
    }
}
