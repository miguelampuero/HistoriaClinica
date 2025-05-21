<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.URLEncoder" %>
<%
    String mensaje = "";
    String tipoAlerta = "";

    String exito = request.getParameter("exito");
    String error = request.getParameter("error");

    if ("1".equals(exito)) {
        mensaje = "✅ Paciente registrado correctamente.";
        tipoAlerta = "success";
    } else if ("dni".equals(error)) {
        mensaje = "⚠️ Ya existe un paciente registrado con ese DNI.";
        tipoAlerta = "danger";
    } else if ("general".equals(error)) {
        mensaje = "❌ Ocurrió un error al registrar el paciente. Intente nuevamente.";
        tipoAlerta = "danger";
    }

    String nombre = request.getParameter("nombre") != null ? request.getParameter("nombre") : "";
    String dni = request.getParameter("dni") != null ? request.getParameter("dni") : "";
    String direccion = request.getParameter("direccion") != null ? request.getParameter("direccion") : "";
    String telefono = request.getParameter("telefono") != null ? request.getParameter("telefono") : "";
    String email = request.getParameter("email") != null ? request.getParameter("email") : "";
%>

<!DOCTYPE html>
<html>
<head>
    <title>Registro de Paciente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">

    <h2 class="mb-4">Registro de Paciente</h2>

    <% if (!mensaje.isEmpty()) { %>
        <div class="alert alert-<%= tipoAlerta %> alert-dismissible fade show" role="alert">
            <%= mensaje %>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    <% } %>

    <form action="PacienteServlet" method="post" class="row g-3">
        <div class="col-md-6">
            <label for="nombre" class="form-label">Nombre completo:</label>
            <input type="text" class="form-control" name="nombre" id="nombre" value="<%= nombre %>" required>
        </div>
        <div class="col-md-6">
            <label for="dni" class="form-label">DNI:</label>
            <input type="text" class="form-control" name="dni" id="dni" value="<%= dni %>" required>
        </div>
        <div class="col-md-6">
            <label for="direccion" class="form-label">Dirección:</label>
            <input type="text" class="form-control" name="direccion" id="direccion" value="<%= direccion %>" required>
        </div>
        <div class="col-md-6">
            <label for="telefono" class="form-label">Teléfono:</label>
            <input type="text" class="form-control" name="telefono" id="telefono" value="<%= telefono %>" required>
        </div>
        <div class="col-md-6">
            <label for="email" class="form-label">Correo electrónico:</label>
            <input type="email" class="form-control" name="email" id="email" value="<%= email %>">
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Registrar</button>
        </div>
    </form>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>