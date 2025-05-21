package controlador;

import dao.HistoriaClinicaDAO;
import dao.PacienteDAO;
import modelo.HistoriaClinica;
import modelo.Paciente;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class VerHistoriaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));

        HistoriaClinicaDAO dao = new HistoriaClinicaDAO();
        HistoriaClinica historia = dao.obtenerPorPaciente(idPaciente);

        PacienteDAO pacienteDAO = new PacienteDAO();
        Paciente paciente = pacienteDAO.obtenerPorId(idPaciente);

        request.setAttribute("paciente", paciente);
        request.setAttribute("historia", historia);
        request.getRequestDispatcher("verHistoriaClinica.jsp").forward(request, response);
    }
}