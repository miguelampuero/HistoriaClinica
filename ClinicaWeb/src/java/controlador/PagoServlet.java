package controlador;

import dao.PagoDAO;
import modelo.Pago;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;

public class PagoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCita = Integer.parseInt(request.getParameter("idCita"));
        double monto = Double.parseDouble(request.getParameter("monto"));
        Date fechaPago = Date.valueOf(request.getParameter("fechaPago"));

        Pago p = new Pago();
        p.setIdCita(idCita);
        p.setMonto(monto);
        p.setFechaPago(fechaPago);

        boolean exito = new PagoDAO().insertar(p); // Suponiendo que devuelve true si todo fue bien

        if (exito) {
            response.sendRedirect("registroPago.jsp?exito=true");
        } else {
            response.sendRedirect("registroPago.jsp?error=true");
        }
    }
}