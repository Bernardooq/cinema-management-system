package model;

import java.sql.Timestamp;

public class Transaccion {
    private int id;
    private int idUser;
    private int idFuncion;
    private int idCine;
    private Timestamp fecha;
    private int boletos;
    private double precioBoleto;
    private double total;

    // Constructor
    public Transaccion(int idUser, int idFuncion, int idCine, int boletos, double precioBoleto) {
        this.idUser = idUser;
        this.idFuncion = idFuncion;
        this.idCine = idCine;
        this.boletos = boletos;
        this.precioBoleto = precioBoleto;
    }

    public Transaccion(int idUser, int idFuncion, int idCine, int boletos, double precioBoleto, double total) {
        this.idUser = idUser;
        this.idFuncion = idFuncion;
        this.idCine = idCine;
        this.boletos = boletos;
        this.precioBoleto = precioBoleto;
        this.total = total;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(int idFuncion) {
        this.idFuncion = idFuncion;
    }

    public int getIdCine() {
        return idCine;
    }

    public void setIdCine(int idCine) {
        this.idCine = idCine;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public int getBoletos() {
        return boletos;
    }

    public void setBoletos(int boletos) {
        this.boletos = boletos;
    }

    public double getPrecioBoleto() {
        return precioBoleto;
    }

    public void setPrecioBoleto(double precioBoleto) {
        this.precioBoleto = precioBoleto;
    }

}
