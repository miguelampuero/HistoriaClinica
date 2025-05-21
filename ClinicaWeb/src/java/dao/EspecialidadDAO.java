package dao;

import modelo.Especialidad;
import java.sql.*;
import java.util.*;

public class EspecialidadDAO {

    public List<Especialidad> listar() {
        List<Especialidad> lista = new ArrayList<>();
        String sql = "SELECT * FROM especialidad";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Especialidad e = new Especialidad();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                lista.add(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}