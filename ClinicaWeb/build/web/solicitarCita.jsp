<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Paciente"%>
<%@page import="modelo.Programacion"%>
<%@page import="dao.PacienteDAO"%>
<%@page import="dao.ProgramacionDAO"%>
<%
    PacienteDAO pacienteDAO = new PacienteDAO();
    List<Paciente> pacientes = pacienteDAO.listar();

    ProgramacionDAO programacionDAO = new ProgramacionDAO();
    List<Programacion> programaciones = programacionDAO.listar();

    String exito = request.getParameter("exito");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Solicitar Cita Médica</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">

    <h2 class="mb-4">Solicitar Cita Médica</h2>

    <% if ("true".equals(exito)) { %>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            ¡Cita registrada exitosamente!
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    <% } %>

    <form action="CitaServlet" method="post" class="row g-3" onsubmit="return confirmarRegistro()">
        <div class="col-md-6">
            <label for="idPaciente" class="form-label">Paciente:</label>
            <select class="form-select" name="idPaciente" id="idPaciente" required>
                <option value="">Seleccione un paciente</option>
                <% for (Paciente p : pacientes) { %>
                    <option value="<%= p.getId() %>"><%= p.getNombre() %></option>
                <% } %>
            </select>
        </div>
        <div class="col-md-6">
            <label for="idProgramacion" class="form-label">Programación:</label>
            <select class="form-select" name="idProgramacion" id="idProgramacion" required>
                <option value="">Seleccione una programación</option>
                <% for (Programacion prog : programaciones) { %>
                    <option value="<%= prog.getId() %>">
                        <%= prog.getFecha() %> | <%= prog.getHoraInicio() %> - <%= prog.getHoraFin() %>
                    </option>
                <% } %>
            </select>
        </div>
        <div class="col-md-6">
            <label for="fecha" class="form-label">Fecha:</label>
            <input type="date" class="form-control" name="fecha" id="fecha" required>
        </div>
        <div class="col-md-6">
            <label for="estado" class="form-label">Estado:</label>
            <select class="form-select" name="estado" id="estado" required>
                <option value="Pendiente" selected>Pendiente</option>
                <option value="Confirmada">Confirmada</option>
                <option value="Cancelada">Cancelada</option>
            </select>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Registrar Cita</button>
        </div>
    </form>

    <script>
        function confirmarRegistro() {
            return confirm("¿Está seguro de que desea registrar esta cita médica?");
        }
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>