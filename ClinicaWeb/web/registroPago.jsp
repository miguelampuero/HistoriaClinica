<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, modelo.Cita, dao.CitaDAO, dao.PacienteDAO, modelo.Paciente" %>
<%
    CitaDAO citaDAO = new CitaDAO();
    List<Cita> citas = citaDAO.listar();

    PacienteDAO pacienteDAO = new PacienteDAO();

    String exito = request.getParameter("exito");
    String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Pago de Cita</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">

    <h2 class="mb-4">Registrar Pago de Cita</h2>

    <% if ("true".equals(exito)) { %>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            ✅ Pago registrado exitosamente.
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    <% } else if ("true".equals(error)) { %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ❌ Ocurrió un error al registrar el pago.
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    <% } %>

    <form action="PagoServlet" method="post" class="row g-3">
        <div class="col-md-6">
            <label for="idCita" class="form-label">Cita:</label>
            <select class="form-select" id="idCita" name="idCita" required>
                <option value="">Seleccione una cita</option>
                <% for (Cita c : citas) {
                    Paciente paciente = pacienteDAO.buscarPorId(c.getIdPaciente());
                %>
                    <option value="<%= c.getId() %>">
                        ID: <%= c.getId() %> - <%= (paciente != null ? paciente.getNombre() : "Paciente desconocido") %> - <%= c.getFecha() %>
                    </option>
                <% } %>
            </select>
        </div>
        <div class="col-md-6">
            <label for="monto" class="form-label">Monto:</label>
            <input type="number" min="0" step="0.01" class="form-control" id="monto" name="monto" required>
        </div>
        <div class="col-md-6">
            <label for="fechaPago" class="form-label">Fecha de Pago:</label>
            <input type="date" class="form-control" id="fechaPago" name="fechaPago" required>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-success">Registrar Pago</button>
        </div>
    </form>

    <script>
        function confirmarPago() {
            return confirm("¿Está seguro de registrar este pago?");
        }
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
