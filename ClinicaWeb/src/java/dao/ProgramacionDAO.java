package dao;

import modelo.Programacion;
import modelo.Especialidad;
import modelo.Medico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramacionDAO {

    // Método para insertar una nueva programación
public boolean insertar(Programacion p) {
    String sql = "INSERT INTO programacion (id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection con = Conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, p.getIdMedico());
        ps.setDate(2, p.getFecha());
        ps.setString(3, p.getHoraInicio());
        ps.setString(4, p.getHoraFin());
        ps.setString(5, p.getConsultorio());
        ps.setString(6, p.getEstado());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


    // Método para listar todas las programaciones con detalles
    public List<Programacion> listarConDetalles() {
        List<Programacion> lista = new ArrayList<>();
        String sql = "SELECT p.*, m.nombre AS nombre_medico, m.apellido, e.nombre AS especialidad " +
                     "FROM programacion p " +
                     "JOIN medico m ON p.id_medico = m.id " +
                     "JOIN especialidad e ON m.id_especialidad = e.id";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Programacion p = new Programacion();
                p.setId(rs.getInt("id"));
                p.setIdMedico(rs.getInt("id_medico"));
                p.setFecha(rs.getDate("fecha"));
                p.setHoraInicio(rs.getString("hora_inicio"));
                p.setHoraFin(rs.getString("hora_fin"));
                p.setConsultorio(rs.getString("consultorio"));
                p.setEstado(rs.getString("estado"));

                Medico m = new Medico();
                m.setId(rs.getInt("id_medico"));
                m.setNombre(rs.getString("nombre_medico"));
                m.setApellido(rs.getString("apellido"));

                Especialidad e = new Especialidad();
                e.setNombre(rs.getString("especialidad"));
                m.setEspecialidad(e);

                p.setMedico(m);
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Método para listar todas las programaciones
    public List<Programacion> listar() {
        List<Programacion> lista = new ArrayList<>();
        String sql = "SELECT p.*, m.nombre AS nombre_medico, m.apellido AS apellido_medico, e.nombre AS especialidad " +
                     "FROM programacion p " +
                     "JOIN medico m ON p.id_medico = m.id " +
                     "JOIN especialidad e ON m.id_especialidad = e.id " +
                     "ORDER BY p.fecha, p.hora_inicio";
    
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
    
            while (rs.next()) {
                Programacion prog = new Programacion();
                prog.setId(rs.getInt("id"));
                prog.setIdMedico(rs.getInt("id_medico"));
                prog.setFecha(rs.getDate("fecha")); // Correcto: rs.getDate devuelve un Date
                prog.setHoraInicio(rs.getString("hora_inicio"));
                prog.setHoraFin(rs.getString("hora_fin"));
                prog.setConsultorio(rs.getString("consultorio"));
                prog.setEstado(rs.getString("estado"));
    
                Medico m = new Medico();
                m.setNombre(rs.getString("nombre_medico"));
                m.setApellido(rs.getString("apellido_medico"));
    
                Especialidad e = new Especialidad();
                e.setNombre(rs.getString("especialidad"));
                m.setEspecialidad(e);
    
                prog.setMedico(m);
                lista.add(prog);
            }
    
            System.out.println("Cantidad de programaciones obtenidas en listar(): " + lista.size());
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return lista;
    }

    // Método para listar programaciones por médico
    public List<Programacion> listarPorMedico(int idMedico) {
        List<Programacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM programacion WHERE id_medico = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idMedico);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Programacion p = new Programacion();
                p.setId(rs.getInt("id"));
                p.setIdMedico(rs.getInt("id_medico"));
                p.setFecha(rs.getDate("fecha"));
                p.setHoraInicio(rs.getString("hora_inicio"));
                p.setHoraFin(rs.getString("hora_fin"));
                p.setConsultorio(rs.getString("consultorio"));
                p.setEstado(rs.getString("estado"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Método para obtener todas las especialidades
    public List<String> obtenerEspecialidades() {
        List<String> especialidades = new ArrayList<>();
        String sql = "SELECT DISTINCT especialidad FROM programacion ORDER BY especialidad";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                especialidades.add(rs.getString("especialidad"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return especialidades;
    }

    // Método para listar programaciones por especialidad
    public List<Programacion> listarPorEspecialidad(String especialidad) {
        List<Programacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM programacion WHERE especialidad = ? ORDER BY fecha, hora_inicio";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, especialidad);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Programacion p = new Programacion();
                p.setId(rs.getInt("id"));
                p.setIdMedico(rs.getInt("id_medico"));
                p.setEspecialidad(rs.getString("especialidad"));
                p.setFecha(rs.getDate("fecha"));
                p.setHoraInicio(rs.getString("hora_inicio"));
                p.setHoraFin(rs.getString("hora_fin"));
                p.setConsultorio(rs.getString("consultorio"));
                p.setEstado(rs.getString("estado"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}