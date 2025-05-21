<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Programacion"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Medico"%>

<%
    List<String> especialidades = (List<String>) request.getAttribute("especialidades");
    List<Programacion> lista = (List<Programacion>) request.getAttribute("lista");
    String especialidadSeleccionada = (String) request.getAttribute("especialidadSeleccionada");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consultar Especialidad</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    <h2>Consulta de Programación por Especialidad</h2>
    <form action="ConsultarEspecialidadServlet" method="get" class="row g-3 mb-4">
        <div class="col-md-6">
            <label class="form-label">Especialidad:</label>
            <select name="especialidad" class="form-select" onchange="this.form.submit()">
                <option value="">-- Seleccionar --</option>
                <% for (String esp : especialidades) { %>
                    <option value="<%= esp %>" <%= esp.equals(especialidadSeleccionada) ? "selected" : "" %>><%= esp %></option>
                <% } %>
            </select>
        </div>
    </form>

    <% if (lista != null && !lista.isEmpty()) { %>
        <table class="table table-bordered">
            <thead class="table-primary">
                <tr>
                    <th>Médico</th>
                    <th>Fecha</th>
                    <th>Hora Inicio</th>
                    <th>Hora Fin</th>
                    <th>Consultorio</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <% for (Programacion p : lista) { %>
                    <tr>
                        <td><%= p.getMedico().getNombre() %></td>
                        <td><%= p.getFecha() %></td>
                        <td><%= p.getHoraInicio() %></td>
                        <td><%= p.getHoraFin() %></td>
                        <td><%= p.getConsultorio() %></td>
                        <td><%= p.getEstado() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else if (especialidadSeleccionada != null) { %>
        <div class="alert alert-warning">No hay programaciones para la especialidad seleccionada.</div>
    <% } %>

</body>
</html>