package com.example.AplicacionDeAtomocidad.Complements;

import com.example.AplicacionDeAtomocidad.models.entities.Cita;
import com.example.AplicacionDeAtomocidad.models.entities.DetalleCita;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CitaRequest {
    private Cita cita;
    private List<DetalleCita> detalles;

    // Constructor vacío
    public CitaRequest() {}

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public List<DetalleCita> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCita> detalles) {
        this.detalles = detalles;
    }
}
