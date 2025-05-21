package controlador;

import dao.PacienteDAO;
import dao.HistoriaClinicaDAO;
import modelo.Paciente;
import modelo.HistoriaClinica;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.net.URLEncoder;

public class PacienteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String dni = request.getParameter("dni");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");

        Paciente p = new Paciente();
        p.setNombre(nombre);
        p.setDni(dni);
        p.setDireccion(direccion);
        p.setTelefono(telefono);
        p.setEmail(email);

        PacienteDAO pacienteDAO = new PacienteDAO();

        try {
            pacienteDAO.insertar(p);
            int idPaciente = pacienteDAO.obtenerIdPorDNI(dni);

            HistoriaClinica hc = new HistoriaClinica();
            hc.setIdPaciente(idPaciente);
            hc.setDescripcion("Primera historia clínica");

            new HistoriaClinicaDAO().insertar(hc);

            // Registro exitoso
            response.sendRedirect("registroPaciente.jsp?exito=1");

        } catch (Exception e) {
            // Verificar si es un error por duplicado de DNI
            String errorParam = "general";
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException || e.getMessage().contains("Duplicate entry")) {
                errorParam = "dni";
            }

            // Reenviar datos ingresados para que el formulario los conserve
            String redirectUrl = String.format(
                "registroPaciente.jsp?error=%s&nombre=%s&dni=%s&direccion=%s&telefono=%s&email=%s",
                errorParam,
                URLEncoder.encode(nombre, "UTF-8"),
                URLEncoder.encode(dni, "UTF-8"),
                URLEncoder.encode(direccion, "UTF-8"),
                URLEncoder.encode(telefono, "UTF-8"),
                URLEncoder.encode(email, "UTF-8")
            );

            response.sendRedirect(redirectUrl);
        }
    }
}
