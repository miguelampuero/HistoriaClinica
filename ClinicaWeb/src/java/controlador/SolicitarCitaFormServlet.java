package controlador;

import dao.PacienteDAO;
import dao.ProgramacionDAO;
import modelo.Paciente;
import modelo.Programacion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/formCita")
public class SolicitarCitaFormServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PacienteDAO pacienteDAO = new PacienteDAO();
        ProgramacionDAO programacionDAO = new ProgramacionDAO();

        List<Paciente> pacientes = pacienteDAO.listar();
        List<Programacion> programaciones = programacionDAO.listar();

        request.setAttribute("pacientes", pacientes);
        request.setAttribute("programaciones", programaciones);

        request.getRequestDispatcher("solicitarCita.jsp").forward(request, response);
    }
}