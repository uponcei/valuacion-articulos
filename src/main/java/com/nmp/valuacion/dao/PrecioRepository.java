package com.nmp.valuacion.dao;

import com.nmp.valuacion.model.Precios;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Nombre: PrecioRepository
 * Descripcion: Repositorio que realiza las consultas sobre la entidad {@link Precios}
 *
 * @author Uriel P. Ibarra
 * @version 0.1
 */
@Repository
public interface PrecioRepository extends MongoRepository<Precios,String> {

    /**
     * Metodo que obtiene un {@link Precios} del material con un código especifico
     * @param codigo identificador del material
     * @return Objeto Precio
     */
     Precios findByCodigo(String codigo);
}
