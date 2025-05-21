package controlador;

import dao.ProgramacionDAO;
import modelo.Programacion;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class ConsultarHorarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ProgramacionDAO dao = new ProgramacionDAO();
            List<Programacion> lista = dao.listar();
    
            // Log para depuración
            System.out.println("Cantidad de programaciones obtenidas: " + (lista != null ? lista.size() : 0));
    
            // Enviar la lista al JSP
            request.setAttribute("lista", lista);
            request.getRequestDispatcher("consultarHorario.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al consultar las programaciones: " + e.getMessage());
            request.getRequestDispatcher("consultarHorario.jsp").forward(request, response);
        }
    }

    // También puedes soportar POST si lo necesitas
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}