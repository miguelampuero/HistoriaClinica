package modelo;

import java.sql.Date;

public class Programacion {
    private int id;
    private int idMedico;
    private Date fecha;
    private String horaInicio;
    private String horaFin;
    private String consultorio;
    private String estado;
    private String especialidad;

    private Medico medico; // ✅ Asociación con la clase Medico

    // Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdMedico() {
        return idMedico;
    }
    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getConsultorio() {
        return consultorio;
    }
    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    // ✅ Getter y Setter para Medico
    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
