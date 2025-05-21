<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Paciente, modelo.HistoriaClinica" %>
<%
    Paciente paciente = (Paciente) request.getAttribute("paciente");
    HistoriaClinica historia = (HistoriaClinica) request.getAttribute("historia");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Historia Clínica</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    <h2 class="mb-4">Historia Clínica del Paciente</h2>

    <% if (paciente != null) { %>
        <div class="mb-3">
            <p><strong>Nombre:</strong> <%= paciente.getNombre() %></p>
        </div>
    <% } else { %>
        <div class="alert alert-warning">Paciente no encontrado.</div>
    <% } %>

    <% if (historia != null) { %>
        <div class="alert alert-secondary">
            <strong>Descripción de Historia Clínica:</strong>
            <p><%= historia.getDescripcion() %></p>
        </div>
    <% } else { %>
        <div class="alert alert-warning">No se encontró historia clínica para este paciente.</div>
    <% } %>

    <a href="buscarHistoria.jsp" class="btn btn-secondary">Volver</a>
</body>
</html>