package controlador;

import dao.MedicoDAO;
import modelo.Medico;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class RegistrarMedicoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener los datos del formulario
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            int idEspecialidad = Integer.parseInt(request.getParameter("idEspecialidad"));

            // Crear objeto Medico
            Medico medico = new Medico();
            medico.setNombre(nombre);
            medico.setApellido(apellido);
            medico.setIdEspecialidad(idEspecialidad);

            // Guardar en base de datos
            MedicoDAO medicoDAO = new MedicoDAO();
            boolean registrado = medicoDAO.registrar(medico);

            // Redirigir con mensaje
            if (registrado) {
                response.sendRedirect("registrarMedico.jsp?exito=1");
            } else {
                response.sendRedirect("registrarMedico.jsp?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("registrarMedico.jsp?error=1");
        }
    }
}