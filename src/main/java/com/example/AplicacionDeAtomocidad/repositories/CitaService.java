package com.example.AplicacionDeAtomocidad.repositories;

import com.example.AplicacionDeAtomocidad.dao.CitaDAO;
import com.example.AplicacionDeAtomocidad.models.entities.Cita;
import com.example.AplicacionDeAtomocidad.models.entities.DetalleCita;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaDAO citaDAO;

    @Transactional
    public void agendarCita(Cita cita, List<DetalleCita> detalles) {
        citaDAO.insertarCitaConDetalles(cita, detalles);
    }
}
