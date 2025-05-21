package dao;

import modelo.HistoriaClinica;
import java.sql.*;

public class HistoriaClinicaDAO {

    public int insertar(HistoriaClinica h) {
        int idGenerado = -1;
        String sql = "INSERT INTO historia_clinica (id_paciente, descripcion) VALUES (?, ?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, h.getIdPaciente());
            ps.setString(2, h.getDescripcion());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idGenerado;
    }
    
    public HistoriaClinica obtenerPorPaciente(int idPaciente) {
    HistoriaClinica hc = null;
    String sql = "SELECT * FROM historia_clinica WHERE id_paciente = ?";

    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, idPaciente);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            hc = new HistoriaClinica();
            hc.setId(rs.getInt("id"));
            hc.setIdPaciente(rs.getInt("id_paciente"));
            hc.setDescripcion(rs.getString("descripcion"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return hc;
}

}