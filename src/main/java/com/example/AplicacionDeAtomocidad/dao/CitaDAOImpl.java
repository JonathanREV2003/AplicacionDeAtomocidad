package com.example.AplicacionDeAtomocidad.dao;

import com.example.AplicacionDeAtomocidad.models.entities.Cita;
import com.example.AplicacionDeAtomocidad.models.entities.DetalleCita;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


@Repository
public class CitaDAOImpl extends CitaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public void insertarCitaConDetalles(Cita cita, List<DetalleCita> detalles) {
        String sqlCita = "INSERT INTO Cita (codigoSucursal, numeroCita, fechaCita, observaciones, codigoEmpleado, tipoCita, codigoCliente) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        String sqlDetalle = "INSERT INTO DetalleCita (correlativo, numeroCita, numeroFicha, codigoServicio, inicio, fin, codigoEmpleado, codigoSucursal) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlCorrelativo = "SELECT NEXT VALUE FOR Seq_Correlativo"; // Nueva consulta para obtener el correlativo

        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción

            // Insertar la cita
            try (PreparedStatement stmtCita = conn.prepareStatement(sqlCita, Statement.RETURN_GENERATED_KEYS)) {
                stmtCita.setInt(1, cita.getCodigoSucursal());
                stmtCita.setInt(2, cita.getNumeroCita());
                stmtCita.setDate(3, new java.sql.Date(cita.getFechaCita().getTime()));
                stmtCita.setString(4, cita.getObservaciones());
                stmtCita.setInt(5, cita.getCodigoEmpleado());
                stmtCita.setString(6, cita.getTipoCita());
                stmtCita.setInt(7, cita.getCodigoCliente());

                stmtCita.executeUpdate();
            }

            try (PreparedStatement stmtDetalle = conn.prepareStatement(sqlDetalle);
                 PreparedStatement stmtCorrelativo = conn.prepareStatement(sqlCorrelativo)) {

                for (DetalleCita detalle : detalles) {
                    ResultSet rs = stmtCorrelativo.executeQuery(); // Obtener el siguiente correlativo
                    int nuevoCorrelativo = rs.next() ? rs.getInt(1) : 0;

                    stmtDetalle.setInt(1, nuevoCorrelativo);
                    stmtDetalle.setInt(2, cita.getNumeroCita());
                    stmtDetalle.setInt(3, detalle.getNumeroFicha());
                    stmtDetalle.setInt(4, detalle.getCodigoServicio());
                    stmtDetalle.setTimestamp(5, new java.sql.Timestamp(detalle.getInicio().getTime()));
                    stmtDetalle.setTimestamp(6, new java.sql.Timestamp(detalle.getFin().getTime()));
                    stmtDetalle.setInt(7, detalle.getCodigoEmpleado());
                    stmtDetalle.setInt(8, detalle.getCodigoSucursal());

                    stmtDetalle.addBatch();
                }
                stmtDetalle.executeBatch();
            }

            conn.commit(); // Confirmar la transacción
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar la cita y sus detalles: " + e.getMessage());
        }
    }
}

