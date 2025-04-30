package model;

public class Cine {
    private int id;
    private String estado;
    private String ciudad;
    private String nombre;

    // Constructor
    public Cine(int id, String estado, String ciudad, String nombre) {
        this.id = id;
        this.estado = estado;
        this.ciudad = ciudad;
        this.nombre = nombre;
    }

    public Cine(String estado, String ciudad, String nombre) {
        this.estado = estado;
        this.ciudad = ciudad;
        this.nombre = nombre;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInfo() {
        return "ID: " + this.getId() + "\tCiudad: " + this.getCiudad() + "\tEstado: " + this.getEstado() + "\tNombre: "
                + this.getNombre();
    }
}
