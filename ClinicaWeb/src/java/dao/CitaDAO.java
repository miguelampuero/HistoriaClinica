package dao;

import modelo.Cita;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    public int insertar(Cita c) {
        int idGenerado = -1;
        String sql = "INSERT INTO cita (id_paciente, id_programacion, fecha, estado) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, c.getIdPaciente());
            ps.setInt(2, c.getIdProgramacion());
            ps.setDate(3, new java.sql.Date(c.getFecha().getTime()));
            ps.setString(4, c.getEstado());
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

    public List<Cita> listar() {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM cita";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cita c = new Cita();
                c.setId(rs.getInt("id"));
                c.setIdPaciente(rs.getInt("id_paciente"));
                c.setIdProgramacion(rs.getInt("id_programacion"));
                c.setFecha(rs.getDate("fecha"));
                c.setEstado(rs.getString("estado"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}