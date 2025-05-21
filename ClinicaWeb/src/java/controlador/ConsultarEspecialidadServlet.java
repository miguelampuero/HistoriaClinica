package controlador;

import dao.ProgramacionDAO;
import modelo.Programacion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet para consultar horarios por especialidad médica.
 */
@WebServlet("/ConsultarEspecialidadServlet")
public class ConsultarEspecialidadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String especialidad = request.getParameter("especialidad");

        ProgramacionDAO dao = new ProgramacionDAO();
        List<String> especialidades = dao.obtenerEspecialidades();
        List<Programacion> lista = null;

        if (especialidad != null && !especialidad.isEmpty()) {
            lista = dao.listarPorEspecialidad(especialidad);
        }

        request.setAttribute("especialidades", especialidades);
        request.setAttribute("especialidadSeleccionada", especialidad);
        request.setAttribute("lista", lista);

        request.getRequestDispatcher("consultarEspecialidad.jsp").forward(request, response);
    }
}