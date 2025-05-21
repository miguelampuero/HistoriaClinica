<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, modelo.Paciente, dao.PacienteDAO" %>
<%
    PacienteDAO pacienteDAO = new PacienteDAO();
    List<Paciente> pacientes = pacienteDAO.obtenerTodos(); // Este método debe estar en tu DAO
%>
<!DOCTYPE html>
<html>
<head>
    <title>Buscar Historia Clínica</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    <h2 class="mb-4">Seleccionar Paciente</h2>

    <form action="verHistoria" method="post">
        <div class="mb-3">
            <label for="idPaciente" class="form-label">Paciente:</label>
            <select name="idPaciente" id="idPaciente" class="form-select" required>
                <% for (Paciente p : pacientes) { %>
                    <option value="<%= p.getId() %>"><%= p.getNombre() %> - <%= p.getDni() %></option>
                <% } %>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Ver Historia</button>
    </form>

    <a href="index.jsp" class="btn btn-secondary mt-3">Volver al inicio</a>
</body>
</html>
