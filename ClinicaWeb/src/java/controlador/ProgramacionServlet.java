package controlador;

import dao.ProgramacionDAO;
import modelo.Programacion;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalTime;
import java.time.Duration;

public class ProgramacionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idMedico = Integer.parseInt(request.getParameter("idMedico"));
            Date fecha = Date.valueOf(request.getParameter("fecha"));
            String horaInicioStr = request.getParameter("horaInicio");
            String horaFinStr = request.getParameter("horaFin");
            String consultorio = request.getParameter("consultorio");
            String estado = request.getParameter("estado");

            // Validar que la diferencia sea de 20 minutos
            LocalTime horaInicio = LocalTime.parse(horaInicioStr);
            LocalTime horaFin = LocalTime.parse(horaFinStr);
            Duration duracion = Duration.between(horaInicio, horaFin);

            if (duracion.toMinutes() != 20) {
                response.sendRedirect("registrarHorario.jsp?error=duracion");
                return;
            }

            Programacion p = new Programacion();
            p.setIdMedico(idMedico);
            p.setFecha(fecha);
            p.setHoraInicio(horaInicioStr);
            p.setHoraFin(horaFinStr);
            p.setConsultorio(consultorio);
            p.setEstado(estado);

            boolean registrado = new ProgramacionDAO().insertar(p);

            if (registrado) {
                response.sendRedirect("registrarHorario.jsp?exito=1");
            } else {
                response.sendRedirect("registrarHorario.jsp?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("registrarHorario.jsp?error=1");
        }
    }
}