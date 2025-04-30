import model.Cine;
import model.Funcion;
import model.Transaccion;
import model.User;

import java.io.Console;
import java.sql.Date;
import java.util.List;

import dao.cinedao;
import dao.funciondao;
import dao.transacciondao;

public class UserMenu {
    private User usuario;
    Cine cineSeleccionado;
    private cinedao cd;
    private funciondao fd;
    private transacciondao td;

    public UserMenu(User usuario) {
        this.usuario = usuario;
        this.cd = new cinedao();
        this.fd = new funciondao();
        this.td = new transacciondao();
        System.out.println("\nBienvenido, " + usuario.getUsername() + "!");
        System.out.println("Elegir Estado, Ciudad y cine");
        elegirCine();
        mostrarMenu();
    }

    private void mostrarMenu() {
        while (true) {

            System.out.println("1. Ver cartelera");
            System.out.println("2. Filtrar funciones");
            System.out.println("3. Comprar boleto");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(System.console().readLine());

            switch (opcion) {
                case 1:
                    verCartelera();
                    break;
                case 2:
                    filtrarFunciones();
                    break;
                case 3:
                    comprarBoleto();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    public void elegirCine() {
        System.out.print("Seleccione el estado:");
        List<String> estados = cd.obtenerEstados();
        mostrarOpciones(estados);
        int opcionEstado = Integer.parseInt(System.console().readLine()) - 1;

        if (opcionEstado < 0 || opcionEstado >= estados.size()) {
            System.out.println("Opción inválida.");
            return;
        }

        String estadoSeleccionado = estados.get(opcionEstado);
        List<String> ciudades = cd.obtenerCiudadesDeCines(estadoSeleccionado);
        System.out.println("\nCiudades disponibles en el estado " + estadoSeleccionado + ":");
        mostrarOpciones(ciudades);

        int opcionCiudad = Integer.parseInt(System.console().readLine()) - 1;
        if (opcionCiudad < 0 || opcionCiudad >= ciudades.size()) {
            System.out.println("Opción inválida.");
            return;
        }

        String ciudadSeleccionada = ciudades.get(opcionCiudad);
        List<Cine> cinesPorCiudadEstado = cd.obtenerCinePorCiudadEstado(estadoSeleccionado, ciudadSeleccionada);

        System.out.println("\nCines disponibles en " + ciudadSeleccionada + ":");
        int i = 1;
        for (Cine cinee : cinesPorCiudadEstado) {
            System.out.println(i + " -\t\t" + cinee.getInfo());
            i++;
        }

        int opcionCine = Integer.parseInt(System.console().readLine()) - 1;
        if (opcionCine < 0 || opcionCine >= cinesPorCiudadEstado.size()) {
            System.out.println("Opción inválida.");
            return;
        }

        cineSeleccionado = cinesPorCiudadEstado.get(opcionCine);
        System.out.println("Cine seleccionado: " + cineSeleccionado.getNombre());
    }

    private void mostrarOpciones(List<String> opciones) {
        for (int i = 0; i < opciones.size(); i++) {
            System.out.println((i + 1) + ". " + opciones.get(i));
        }
        System.out.print("Seleccione una opción: ");
    }

    private void verCartelera() {
        System.out.println("Mostrando cartelera...");

        try {
            System.out.println(cineSeleccionado.getId());
            List<Funcion> funciones = fd.obtenerTodasfuncionesPorCine(cineSeleccionado.getId());
            for (Funcion func : funciones) {
                System.out.println(func.printInfo());
            }

        } catch (Exception e) {
            System.out.println("Error al obtener las funciones");
        }

    }

    private void filtrarFunciones() {
        System.out.println("Filtrando funciones... (pendiente de implementar)");

        System.out.print("Ingrese el ID de la película (o presione Enter para no filtrar): ");
        String inputPelicula = System.console().readLine();
        Integer idPelicula = inputPelicula.isEmpty() ? null : Integer.parseInt(inputPelicula);

        System.out.print("Ingrese el Nombre de la película (o presione Enter para no filtrar): ");
        String inputNameP = System.console().readLine();
        String Namep = inputNameP.isEmpty() ? null : inputNameP;

        System.out.print("Ingrese el número de la sala (o presione Enter para no filtrar): ");
        String inputSala = System.console().readLine();
        Integer sala = inputSala.isEmpty() ? null : Integer.parseInt(inputSala);

        System.out.print("Ingrese la fecha (YYYY-MM-DD) para filtrar (o presione Enter para no filtrar): ");
        String inputFecha = System.console().readLine();
        Date fecha = inputFecha.isEmpty() ? null : Date.valueOf(inputFecha);

        List<Funcion> funcionesFiltradas = fd.obtenerFuncionesPorFiltros(cineSeleccionado.getId(), idPelicula,
                Namep, sala,
                fecha);

        if (funcionesFiltradas.isEmpty()) {
            System.out.println("No se encontraron funciones con los filtros especificados.");
        } else {
            System.out.println("\nFunciones filtradas:");
            for (Funcion funcion : funcionesFiltradas) {
                System.out.println(funcion.printInfo());
            }
        }
    }

    private void comprarBoleto() {
        System.out.println("Comprando boletos");
        System.out.print("Id de la funcion: ");
        Integer idUser = usuario.getId();
        Integer idFuncion = Integer.parseInt(System.console().readLine());
        Integer idCine = cineSeleccionado.getId();
        Funcion func = fd.obtenerfuncionPorId(idFuncion);
        System.out.println();
        System.out.print("Cantidad de boletos: ");
        Integer boletos = Integer.parseInt(System.console().readLine());
        double precioBol = func.getPrecio();
        if (func.getCapacidad() == func.getCapacidadActual()) {
            System.out.println("Boletos agotados");
            return;
        }
        while ((boletos + func.getCapacidadActual()) > func.getCapacidad()) {
            System.out.print("Cantidad de boletos sobrepasa el limite de la sala, intente de nuevo: ");
            boletos = Integer.parseInt(System.console().readLine());
        }
        Transaccion tr = new Transaccion(idUser, idFuncion, idCine, boletos, precioBol);
        td.agregartransaccion(tr);
        fd.actualizarcapacidad(idFuncion, boletos);
    }
}
