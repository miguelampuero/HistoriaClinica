package dao;

import modelo.Pago;
import java.sql.*;

public class PagoDAO {

public boolean insertar(Pago p) {
    String sql = "INSERT INTO pago (id_cita, monto, fecha_pago) VALUES (?, ?, ?)";
    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, p.getIdCita());
        ps.setDouble(2, p.getMonto());
        ps.setDate(3, p.getFechaPago());

        int filas = ps.executeUpdate();
        return filas > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    public void actualizarEstadoProgramacion(int idProgramacion, String estado) {
        String sql = "UPDATE programacion SET estado = ? WHERE id = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, idProgramacion);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}