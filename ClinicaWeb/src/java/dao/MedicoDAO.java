package dao;

import modelo.Medico;
import java.sql.*;
import java.util.*;

public class MedicoDAO {

    public List<Medico> listarPorEspecialidad(int idEspecialidad) {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM medico WHERE id_especialidad = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEspecialidad);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Medico m = new Medico();
                m.setId(rs.getInt("id"));
                m.setNombre(rs.getString("nombre"));
                m.setIdEspecialidad(rs.getInt("id_especialidad"));
                lista.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public List<Medico> listar() throws SQLException {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, id_especialidad FROM medico";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setNombre(rs.getString("nombre"));
                medico.setApellido(rs.getString("apellido"));
                medico.setIdEspecialidad(rs.getInt("id_especialidad"));
                lista.add(medico);
            }
        }
        return lista;
    }

public Medico obtenerPorId(int idMedico) {
    Medico medico = null;
    String sql = "SELECT * FROM medico WHERE id = ?";
    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idMedico);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            medico = new Medico();
            medico.setId(rs.getInt("id"));
            medico.setNombre(rs.getString("nombre"));
            medico.setApellido(rs.getString("apellido"));
            medico.setIdEspecialidad(rs.getInt("id_especialidad"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return medico;
}

public boolean registrar(Medico medico) throws SQLException {
    String sql = "INSERT INTO medico (nombre, apellido, id_especialidad) VALUES (?, ?, ?)";
    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, medico.getNombre());
        ps.setString(2, medico.getApellido()); // Nuevo
        ps.setInt(3, medico.getIdEspecialidad());
        return ps.executeUpdate() > 0;
    }
}

}