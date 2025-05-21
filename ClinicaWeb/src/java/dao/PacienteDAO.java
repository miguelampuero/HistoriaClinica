package dao;

import modelo.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public int insertar(Paciente p) {
        int idGenerado = -1;
        String sql = "INSERT INTO paciente (nombre, dni, direccion, telefono, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDni());
            ps.setString(3, p.getDireccion());
            ps.setString(4, p.getTelefono());
            ps.setString(5, p.getEmail());
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
    
    public List<Paciente> obtenerTodos() {
    List<Paciente> lista = new ArrayList<>();
    String sql = "SELECT * FROM paciente";

    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Paciente p = new Paciente();
            p.setId(rs.getInt("id"));
            p.setNombre(rs.getString("nombre"));
            p.setDni(rs.getString("dni"));
            p.setDireccion(rs.getString("direccion"));
            p.setTelefono(rs.getString("telefono"));
            p.setEmail(rs.getString("email"));
            lista.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
}

    
    public Paciente obtenerPorId(int id) {
    Paciente paciente = null;
    String sql = "SELECT * FROM paciente WHERE id = ?";

    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            paciente = new Paciente();
            paciente.setId(rs.getInt("id"));
            paciente.setNombre(rs.getString("nombre"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return paciente;
}


    public int obtenerIdPorDNI(String dni) {
        int id = -1;
        String sql = "SELECT id FROM paciente WHERE dni = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Paciente> listar() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM paciente";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public Paciente buscarPorId(int id) {
    Paciente paciente = null;
    String sql = "SELECT * FROM paciente WHERE id = ?";

    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            paciente = new Paciente();
            paciente.setId(rs.getInt("id"));
            paciente.setNombre(rs.getString("nombre"));
            paciente.setDni(rs.getString("dni"));
            paciente.setDireccion(rs.getString("direccion"));
            paciente.setTelefono(rs.getString("telefono"));
            paciente.setEmail(rs.getString("email"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return paciente;
}

}