package model;

import java.sql.Date;
import java.sql.Time;

public class Funcion {
    private int id;
    private int idCine;
    private int idPelicula;
    private String nombre;
    private int sala;
    private Date fecha;
    private Time hora;
    private double precio;
    private int capacidad;
    private int capacidadActual;

    // Constructor
    public Funcion(int id, int idCine, int idPelicula, String nombre, int sala, Date fecha, Time hora, double precio,
            int capacidad,
            int capacidadActual) {
        this.id = id;
        this.idCine = idCine;
        this.idPelicula = idPelicula;
        this.nombre = nombre;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.capacidad = capacidad;
        this.capacidadActual = capacidadActual;
    }

    public Funcion(int idCine, int idPelicula, String nombre, int sala, Date fecha, Time hora, double precio,
            int capacidad,
            int capacidadActual) {
        this.idCine = idCine;
        this.idPelicula = idPelicula;
        this.nombre = nombre;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.capacidad = capacidad;
        this.capacidadActual = capacidadActual;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCine() {
        return idCine;
    }

    public void setIdCine(int idCine) {
        this.idCine = idCine;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    public String printInfo() {
        return String.format(
                "ID Función: %d\tID Cine: %d\tID Película: %d\tNombre: %s\tSala: %d\tFecha: %s\tHora: %s\tPrecio: %.2f\tCapacidad: %d\tCapacidad Actual: %d",
                id, idCine, idPelicula, nombre, sala, fecha != null ? fecha.toString() : "No disponible",
                hora != null ? hora.toString() : "No disponible", precio, capacidad, capacidadActual);
    }
}
