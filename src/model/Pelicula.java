package model;

public class Pelicula {
    private int id;
    private String nombre;
    private String director;
    private String productor;
    private String clasificacion;
    private float duracion;
    private String genero;

    // Constructor sin id (ideal para la creación de una nueva película)
    public Pelicula(String nombre, String director, String productor, String clasificacion, float duracion,
            String genero) {
        this.nombre = nombre;
        this.director = director;
        this.productor = productor;
        this.clasificacion = clasificacion;
        this.duracion = duracion;
        this.genero = genero;
    }

    // Constructor con id (ideal para obtener una película de la base de datos)
    public Pelicula(int id, String nombre, String director, String productor, String clasificacion, float duracion,
            String genero) {
        this.id = id;
        this.nombre = nombre;
        this.director = director;
        this.productor = productor;
        this.clasificacion = clasificacion;
        this.duracion = duracion;
        this.genero = genero;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String toString() {
        return "Pelicula{id=" + id + ", nombre='" + nombre + "', director='" + director + "', productor='" + productor +
                "', clasificacion='" + clasificacion + "', duracion=" + duracion + ", genero='" + genero + "'}";
    }
}
