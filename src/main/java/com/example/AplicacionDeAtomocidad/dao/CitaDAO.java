package com.example.AplicacionDeAtomocidad.dao;

import com.example.AplicacionDeAtomocidad.models.entities.Cita;
import com.example.AplicacionDeAtomocidad.models.entities.DetalleCita;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//llamamos al Procedimiento almacenado
@Repository
public abstract class CitaDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void InsertarCitaConDetalles(Cita cita, List<DetalleCita> detalles){

        String detallesJson = convertirDetallesAJson(detalles);

        Query query = entityManager.createNativeQuery("CALL InsertarCitaConDetalles(:codigoSucursal, :numeroCita, :fechaCita, :observaciones, :codigoEmpleado, :tipoCita, :codigoCliente, :detalles)")
                .setParameter("codigoSucursal", cita.getCodigoSucursal())
                .setParameter("numeroCita", cita.getNumeroCita())
                .setParameter("fechaCita", cita.getFechaCita())
                .setParameter("observaciones", cita.getObservaciones())
                .setParameter("codigoEmpleado", cita.getCodigoEmpleado())
                .setParameter("tipoCita", cita.getTipoCita())
                .setParameter("codigoCliente", cita.getCodigoCliente())
                .setParameter("detalles", detallesJson);

        query.executeUpdate();
    }
    private String convertirDetallesAJson(List<DetalleCita> detalles) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(detalles);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error convirtiendo detalles a JSON", e);
        }
    }

    @jakarta.transaction.Transactional
    public abstract void insertarCitaConDetalles(Cita cita, List<DetalleCita> detalles);
}
