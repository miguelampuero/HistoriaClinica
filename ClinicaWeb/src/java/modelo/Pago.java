package modelo;

import java.sql.Date;

public class Pago {
    private int id;
    private int idCita;
    private double monto;
    private Date fechaPago;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdCita() {
        return idCita;
    }
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }
}