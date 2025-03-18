package com.example.AplicacionDeAtomocidad.controllers;

import com.example.AplicacionDeAtomocidad.Complements.CitaRequest;
import com.example.AplicacionDeAtomocidad.repositories.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cita")
public class CitaController {
    @Autowired
    private CitaService citaService;

    @PostMapping("/agendar")
    public ResponseEntity<String> agendarCita(@RequestBody CitaRequest citaRequest) {
        try {
            citaService.agendarCita(citaRequest.getCita(), citaRequest.getDetalles());
            return ResponseEntity.ok("Cita agendada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al agendar cita: " + e.getMessage());
        }
    }
}
