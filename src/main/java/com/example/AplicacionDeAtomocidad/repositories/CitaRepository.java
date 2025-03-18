package com.example.AplicacionDeAtomocidad.repositories;

import com.example.AplicacionDeAtomocidad.models.entities.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
}
