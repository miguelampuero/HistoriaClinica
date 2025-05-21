<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.ProgramacionDAO" %>
<%@ page import="modelo.Programacion" %>
<%@ page import="modelo.Medico" %>
<%@ page import="modelo.Especialidad" %>

<%
    // Obtener los datos desde el DAO
    ProgramacionDAO dao = new ProgramacionDAO();
    List<Programacion> lista = dao.listar();

    if (lista == null) {
        lista = new ArrayList<Programacion>();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Consulta de Horarios Médicos</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Consulta de Horarios Médicos</h2>

    <%
        if (!lista.isEmpty()) {
    %>
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
            <tr>
                <th>Fecha</th>
                <th>Hora Inicio</th>
                <th>Hora Fin</th>
                <th>Médico</th>
                <th>Especialidad</th>
                <th>Consultorio</th>
                <th>Estado</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Programacion p : lista) {
                    Medico m = p.getMedico();
                    Especialidad e = (m != null) ? m.getEspecialidad() : null;
            %>
                <tr>
                    <td><%= p.getFecha() %></td>
                    <td><%= p.getHoraInicio() %></td>
                    <td><%= p.getHoraFin() %></td>
                    <td><%= m != null ? m.getNombre() + " " + m.getApellido() : "No disponible" %></td>
                    <td><%= e != null ? e.getNombre() : "No disponible" %></td>
                    <td><%= p.getConsultorio() != null ? p.getConsultorio() : "No disponible" %></td>
                    <td><%= p.getEstado() != null ? p.getEstado() : "No disponible" %></td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <div class="alert alert-warning">No hay programaciones registradas.</div>
    <%
        }
    %>
</div>
</body>
</html>