import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import dao.cinedao;
import dao.funciondao;
import dao.peliculadao;
import model.Cine;
import model.Funcion;
import model.Pelicula;

public class AdminMenu {

    public AdminMenu() {
        mostrarMenu();
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("\nMenú de Administrador:");
            System.out.println("1. Gestionar Cines");
            System.out.println("2. Gestionar Funciones");
            System.out.println("3. Gestionar Peliculas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = System.console().readLine();

            switch (opcion) {
                case "1":
                    gestionarCines();
                    break;
                case "2":
                    gestionarFunciones();
                    break;
                case "3":
                    gestionarPeliculas();
                    break;
                case "4":
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void gestionarCines() {
        int id;
        String ciudad;
        String estado;
        String nombre;
        Cine cinema;
        List<Cine> cines;
        cinedao cd = new cinedao();
        while (true) {
            System.out.println("\nGestión de Cines:");
            System.out.println("1. Agregar Cine");
            System.out.println("2. Eliminar Cine");
            System.out.println("3. Ver Cines");
            System.out.println("4. Filtro de Cines");
            System.out.println("5. Editar Cine");
            System.out.println("6. Regresar");
            System.out.print("Seleccione una opción: ");

            String opcion = System.console().readLine();
            switch (opcion) {
                case "1":
                    System.out.println("Agregar cine");
                    System.out.print("Ingrese el estado: ");
                    estado = System.console().readLine();
                    System.out.print("Ingrese la ciudad: ");
                    ciudad = System.console().readLine();
                    System.out.print("Ingrese el nombre del cine: ");
                    nombre = System.console().readLine();
                    cinema = new Cine(estado, ciudad, nombre);
                    cd.agregarcine(cinema);
                    break;
                case "2":
                    System.out.print("Ingrese el id del cine a eliminar: ");
                    id = Integer.parseInt(System.console().readLine());
                    try {
                        cd.eliminarcine(id);
                    } catch (Exception e) {
                        System.out.println("El cine que quiere eliminar no existe");
                    }
                    break;
                case "3":
                    System.out.println("Lista de cines:");
                    cines = cd.obtenerTodoscines();
                    for (Cine cine : cines) {
                        System.out.println(cine.getInfo());
                    }
                    break;

                case "4":
                    System.out.println("Filtro de cines");
                    System.out.println("Seleccione el criterio de búsqueda:");
                    System.out.println("1. Estado");
                    System.out.println("2. Ciudad");
                    System.out.println("3. Nombre");
                    System.out.print("Ingrese su opción: ");

                    String opcionFiltro = System.console().readLine();
                    List<Cine> cinesFiltrados = new ArrayList<>();

                    switch (opcionFiltro) {
                        case "1":
                            System.out.print("Ingrese el estado: ");
                            String estadoFiltro = System.console().readLine();
                            cinesFiltrados = cd.obtenerCinesPorEstado(estadoFiltro);
                            break;
                        case "2":
                            System.out.print("Ingrese la ciudad: ");
                            String ciudadFiltro = System.console().readLine();
                            cinesFiltrados = cd.obtenerCinesPorCiudad(ciudadFiltro);
                            break;
                        case "3":
                            System.out.print("Ingrese el nombre del cine: ");
                            String nombreFiltro = System.console().readLine();
                            cinesFiltrados = cd.obtenerCinesPorNombre(nombreFiltro);
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }

                    // Mostrar resultados si hay cines filtrados
                    if (!cinesFiltrados.isEmpty()) {
                        System.out.println("Cines encontrados:");
                        for (Cine cine : cinesFiltrados) {
                            System.out.println(cine.getInfo());
                        }
                    } else {
                        System.out.println("No se encontraron cines con ese criterio.");
                    }
                    break;

                case "5":
                    System.out.print("Ingrese el id del cine a editar: ");
                    id = Integer.parseInt(System.console().readLine());
                    Cine cineEncontrado = cd.obtenercinePorId(id);

                    if (cineEncontrado != null) {
                        System.out.println("Cine encontrado: " + cineEncontrado);

                        System.out.print("Ingrese el nuevo estado (enter para omitir): ");
                        String nuevoEstado = System.console().readLine();
                        if (nuevoEstado.isEmpty())
                            nuevoEstado = cineEncontrado.getEstado();

                        System.out.print("Ingrese la nueva ciudad (enter para omitir): ");
                        String nuevaCiudad = System.console().readLine();
                        if (nuevaCiudad.isEmpty())
                            nuevaCiudad = cineEncontrado.getCiudad();

                        System.out.print("Ingrese el nuevo nombre del cine (enter para omitir): ");
                        String nuevoNombre = System.console().readLine();
                        if (nuevoNombre.isEmpty())
                            nuevoNombre = cineEncontrado.getNombre();

                        Cine cineActualizado = new Cine(id, nuevoEstado, nuevaCiudad, nuevoNombre);
                        cd.actualizarcine(cineActualizado);
                        System.out.println("Cine actualizado correctamente.");
                    } else {
                        System.out.println("Cine no encontrado.");
                    }
                    break;

                case "6":
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void gestionarFunciones() {
        funciondao fd = new funciondao();
        int id;
        int idCine;
        int idPelicula;
        int sala;
        Date fecha;
        Time hora;
        Double precio;
        int capacidad;

        while (true) {
            System.out.println("\nGestión de Funciones:");
            System.out.println("1. Agregar Función");
            System.out.println("2. Eliminar Función");
            System.out.println("3. Ver Funciones");
            System.out.println("4. Regresar");
            System.out.print("Seleccione una opción: ");

            String opcion = System.console().readLine();

            switch (opcion) {
                case "1":
                    System.out.print("Ingrese ID del cine: ");
                    idCine = Integer.parseInt(System.console().readLine());
                    System.out.print("Ingrese ID de la película: ");
                    idPelicula = Integer.parseInt(System.console().readLine());
                    System.out.print("Ingrese número de sala: ");
                    sala = Integer.parseInt(System.console().readLine());
                    System.out.print("Ingrese fecha (YYYY-MM-DD): ");
                    fecha = Date.valueOf(System.console().readLine());
                    System.out.print("Ingrese hora (HH:MM:SS): ");
                    hora = Time.valueOf(System.console().readLine());
                    System.out.print("Ingrese precio: ");
                    precio = Double.parseDouble(System.console().readLine());
                    System.out.print("Ingrese capacidad total: ");
                    capacidad = Integer.parseInt(System.console().readLine());

                    peliculadao pdao = new peliculadao();
                    Pelicula p = pdao.obtenerPeliculaPorId(idPelicula);

                    try {
                        Funcion funcion = new Funcion(idCine, idPelicula, p.getNombre(), sala, fecha, hora, precio,
                                capacidad, 0);
                        fd.agregarfuncion(funcion);
                        System.out.println("Funcion agregada exitosamente");
                    } catch (Exception e) {
                        System.out.println("Error al agregar la funcion");
                    }
                    break;
                case "2":
                    System.out.println("Eliminar función");
                    id = Integer.parseInt(System.console().readLine());
                    System.out.print("Ingrese ID de la funcion: ");
                    try {
                        fd.eliminarfuncion(id);
                        System.out.println("Funcion eliminada exitosamente");
                    } catch (Exception e) {
                        System.out.println("Error al eliminar la funcion");
                    }
                    break;
                case "3":
                    System.out.print("Ingrese ID del cine: ");
                    idCine = Integer.parseInt(System.console().readLine());
                    try {
                        List<Funcion> funciones = fd.obtenerTodasfuncionesPorCine(idCine);
                        for (Funcion func : funciones) {
                            System.out.println(func.printInfo());
                        }

                    } catch (Exception e) {
                        System.out.println("Error al obtener las funciones");
                    }
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void gestionarPeliculas() {
        peliculadao pd = new peliculadao();
        String nombre, director, productor, clasificacion, genero;
        float duracion;

        while (true) {
            System.out.println("\nGestión de Películas:");
            System.out.println("1. Agregar Película");
            System.out.println("2. Eliminar Película");
            System.out.println("3. Ver Películas");
            System.out.println("4. Regresar");
            System.out.print("Seleccione una opción: ");

            String opcion = System.console().readLine();

            switch (opcion) {
                case "1":
                    System.out.print("Ingrese nombre de la película: ");
                    nombre = System.console().readLine();
                    System.out.print("Ingrese director de la película: ");
                    director = System.console().readLine();
                    System.out.print("Ingrese productor de la película: ");
                    productor = System.console().readLine();
                    System.out.print("Ingrese clasificación de la película: ");
                    clasificacion = System.console().readLine();
                    System.out.print("Ingrese duración de la película (en minutos): ");
                    duracion = Float.parseFloat(System.console().readLine());
                    System.out.print("Ingrese género de la película: ");
                    genero = System.console().readLine();

                    // Crear objeto de pelis y guardar
                    Pelicula pelicula = new Pelicula(0, nombre, director, productor, clasificacion, duracion, genero);
                    boolean result = pd.agregarPelicula(pelicula);

                    if (result) {
                        System.out.println("Película agregada exitosamente.");
                    } else {
                        System.out.println("Hubo un error al agregar la película.");
                    }
                    break;

                case "2":
                    // Eliminar pelis
                    System.out.print("Ingrese el ID de la película a eliminar: ");
                    int idEliminar = Integer.parseInt(System.console().readLine());
                    boolean eliminado = pd.eliminarPelicula(idEliminar);

                    if (eliminado) {
                        System.out.println("Película eliminada exitosamente.");
                    } else {
                        System.out.println("No se pudo eliminar la película.");
                    }
                    break;

                case "3":
                    System.out.println("Lista de películas ");
                    List<Pelicula> pelis = pd.obtenerTodasLasPeliculas();
                    for (Pelicula peli : pelis) {
                        System.out.println(peli.toString());
                    }
                    break;

                case "4":
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

}
