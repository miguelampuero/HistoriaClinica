<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, dao.MedicoDAO, modelo.Medico" %>
<%
    String exito = request.getParameter("exito");
    String error = request.getParameter("error");

    MedicoDAO medicoDAO = new MedicoDAO();
    List<Medico> listaMedicos = medicoDAO.listar();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Horario Médico</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

    <h2 class="mb-4">Registrar Horario Médico</h2>

    <% if ("1".equals(exito)) { %>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            ✅ Horario registrado correctamente.
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    <% } else if ("1".equals(error)) { %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ❌ Ocurrió un error al registrar el horario.
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    <% } else if ("duracion".equals(error)) { %>
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
            ⚠️ La duración del horario debe ser exactamente de 20 minutos.
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    <% } %>

    <form action="ProgramacionServlet" method="post" class="row g-3">
        <div class="col-md-6">
            <label for="idMedico" class="form-label">Médico:</label>
            <select class="form-select" id="idMedico" name="idMedico" required>
                <option value="">Seleccione un médico</option>
                <% for (Medico m : listaMedicos) { %>
                    <option value="<%= m.getId() %>">
                        <%= m.getNombre() %> <%= m.getApellido() %>
                        <% if (m.getEspecialidad() != null) { %>
                            - <%= m.getEspecialidad().getNombre() %>
                        <% } %>
                    </option>
                <% } %>
            </select>
        </div>

        <div class="col-md-6">
            <label for="fecha" class="form-label">Fecha:</label>
            <input type="date" class="form-control" id="fecha" name="fecha" required>
        </div>
        <div class="col-md-6">
            <label for="horaInicio" class="form-label">Hora Inicio:</label>
            <input type="time" class="form-control" id="horaInicio" name="horaInicio" required>
        </div>
        <div class="col-md-6">
            <label for="horaFin" class="form-label">Hora Fin:</label>
            <input type="time" class="form-control" id="horaFin" name="horaFin" required>
        </div>
        <div class="col-md-6">
            <label for="consultorio" class="form-label">Consultorio:</label>
            <input type="text" class="form-control" id="consultorio" name="consultorio" required>
        </div>
        <div class="col-md-6">
            <label for="estado" class="form-label">Estado:</label>
            <select class="form-select" id="estado" name="estado" required>
                <option value="Disponible">Disponible</option>
                <option value="Ocupado">Ocupado</option>
            </select>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Registrar</button>
        </div>
    </form>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>