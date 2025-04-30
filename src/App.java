import dao.userdao;
import model.User;

public class App {
    public static void main(String[] args) throws Exception {
        initialMenu();
    }

    public static void initialMenu() {
        System.out.println("¡Bienvenido!\nInserte 1 para ingresar\nInserte 2 para registrarse");
        int opcion = 0;

        while (opcion != 2 && opcion != 1) {

            opcion = Integer.parseInt(System.console().readLine());
            if (opcion == 1) {
                System.out.println("Ingresar");
                ingresar();
            } else if (opcion == 2) {
                System.out.println("Registrarse");
                registrarse();
            } else {
                System.out.print("Ingrese una opcion Válida: ");
            }
        }
    }

    public static void registrarse() {
        String usertype = "User";
        System.out.println("1. Registrarse como usuario\n2. Registratse como admin (se requiere clave)");
        int opcionRegistro = Integer.parseInt(System.console().readLine());
        if (opcionRegistro == 2) {
            String code = "";
            while (!code.equals("aaa")) {
                System.out.print("Ingrese un codigo de registro valido: ");
                code = System.console().readLine();
            }
            usertype = "Admin";
        }

        System.out.print("Ingrese un correo electronico: ");
        String email = (System.console().readLine());
        userdao ud = new userdao();
        while (ud.obtenerUsuarioPorEmail(email)) {
            System.out.print("Error, correo electronico registado previamente, ingrese otro correo electronico: ");
            email = (System.console().readLine());
        }
        System.out.print("Ingrese una contraseña: ");
        String passwd = System.console().readLine();
        User usuario = new User(email, passwd, usertype);
        if (ud.agregarUsuario(usuario)) {
            System.out.print("Usuario agregado exitosamente");
        } else {
            System.out.print("Error agregando usuario");
        }
        if (usuario.getUsername().equals("Admin")) {
            AdminMenu adminMenu = new AdminMenu();
        } else {
            UserMenu userMenu = new UserMenu(usuario);
        }
    }

    public static void ingresar() {
        System.out.print("Ingrese su email:");
        String email = System.console().readLine();
        System.out.print("Ingrese su contraseña:");
        String passwd = System.console().readLine();
        userdao ud = new userdao();
        while (!ud.obtenerUsuarioPorEmailyPsswdBool(email, passwd)) {
            System.out.print("Error, correo o contraseña incorrectos\nIngrese su email:");
            email = System.console().readLine();
            System.out.print("Ingrese su contraseña:");
            passwd = System.console().readLine();
        }
        User usuario = ud.obtenerUsuarioPorEmailyPsswd(email, passwd);
        if (usuario.getUsername().equals("Admin")) {
            AdminMenu adminMenu = new AdminMenu();
        } else {
            UserMenu userMenu = new UserMenu(usuario);
        }

    }
}
