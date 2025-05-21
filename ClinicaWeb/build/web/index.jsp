<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>🏥 Clínica Web - Inicio</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-light d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow p-5 rounded-4" style="max-width: 700px; width: 100%;">
        <h1 class="text-center mb-4 text-primary">🏥 Clínica Web</h1>

        <h4 class="text-center mb-3 text-secondary">Módulos Disponibles</h4>
        <div class="d-grid gap-3 mb-4">


            <a href="registrarMedico.jsp" class="btn btn-outline-primary btn-lg">
                <i class="bi bi-person-plus-fill me-2"></i> Registrar Médico
            </a>
            
            <a href="registroPaciente.jsp" class="btn btn-outline-warning btn-lg">
                <i class="bi bi-person-vcard-fill me-2"></i> Registrar Paciente
            </a>

            <a href="registrarHorario.jsp" class="btn btn-outline-success btn-lg">
                <i class="bi bi-clock-fill me-2"></i> Registrar Horario
            </a>

            <a href="consultarHorario.jsp" class="btn btn-outline-info btn-lg">
                <i class="bi bi-search-heart-fill me-2"></i> Consultar Horario
            </a>

            <a href="solicitarCita.jsp" class="btn btn-outline-dark btn-lg">
                <i class="bi bi-calendar-plus-fill me-2"></i> Solicitar Cita
            </a>

            <a href="registroPago.jsp" class="btn btn-outline-danger btn-lg">
                <i class="bi bi-cash-coin me-2"></i> Registrar Pago
            </a>
            
            <a href="buscarHistoria.jsp" class="btn btn-outline-secondary btn-lg">
                 <i class="bi bi-journal-medical me-2"></i> Historia Clínica
            </a>
            
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>