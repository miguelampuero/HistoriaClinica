<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.EspecialidadDAO, modelo.Especialidad, java.util.List" %>
<%
    String exito = request.getParameter("exito");
    String error = request.getParameter("error");

    EspecialidadDAO dao = new EspecialidadDAO();
    List<Especialidad> especialidades = dao.listar();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Médico</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">

    <h2 class="mb-4">Registrar Médico</h2>

    <% if ("1".equals(exito)) { %>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            ✅ Médico registrado exitosamente.
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    <% } else if ("1".equals(error)) { %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ❌ Error al registrar el médico.
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    <% } %>

    <form action="RegistrarMedicoServlet" method="post">
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre del Médico:</label>
            <input type="text" id="nombre" name="nombre" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="apellido" class="form-label">Apellido del Médico:</label>
            <input type="text" id="apellido" name="apellido" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="especialidad" class="form-label">Especialidad:</label>
            <select id="especialidad" name="idEspecialidad" class="form-select" required>
                <% for (Especialidad e : especialidades) { %>
                    <option value="<%= e.getId() %>"><%= e.getNombre() %></option>
                <% } %>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Registrar</button>
    </form>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>