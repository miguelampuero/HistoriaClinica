package controlador;

import dao.CitaDAO;
import modelo.Cita;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;

public class CitaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
        int idProgramacion = Integer.parseInt(request.getParameter("idProgramacion"));
        Date fecha = Date.valueOf(request.getParameter("fecha"));
        String estado = request.getParameter("estado");

        Cita c = new Cita();
        c.setIdPaciente(idPaciente);
        c.setIdProgramacion(idProgramacion);
        c.setFecha(fecha);
        c.setEstado(estado);

        new CitaDAO().insertar(c);

        // Redirigir con parámetro de éxito
        response.sendRedirect("solicitarCita.jsp?exito=true");
    }
}
