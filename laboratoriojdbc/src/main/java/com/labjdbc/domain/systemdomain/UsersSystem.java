package com.labjdbc.domain.systemdomain;

import java.util.List;
import java.util.Scanner;

import com.labjdbc.domain.userdomain.User;
import com.labjdbc.domain.userdomain.UserDAO;

public class UsersSystem {

    private Scanner scanner;
    private final String menu = """
            \t.:Menú de usuarios:.
            1. Listar usuarios
            2. Crear usuario
            3. Actualizar usuario
            4. Eliminar usuario
            5. Salir
            Ingrese una opción: \n>>   """;
    private UserDAO userDAO;

    public UsersSystem(Scanner scanner) {
        this.scanner = scanner;
        userDAO = new UserDAO();
    }

    public void menu() {
        int option = -1;
        String input = "";
        do {
            System.out.print(this.menu);
            input = scanner.nextLine();
            try {
                option = Integer.parseInt(input);
                if (option < 1 || option > 5) {
                    throw new NumberFormatException();
                }
                selectOption(option);
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida");
            }
        } while (option != 5);
    }

    public void selectOption(int option) {
        System.out.println("=========================================");
        switch (option) {
            case 1:
                System.out.println(">>Listar Usuarios\n");
                System.out.println();
                listUsers();
                break;
            case 2:
                System.out.println(">>Crear usuario");
                System.out.println();
                createUser();
                break;
            case 3:
                System.out.println(">>Actualizar usuario");
                System.out.println();
                updateUser();
                break;
            case 4:
                System.out.println(">>Eliminar usuario");
                System.out.println();
                deleteUser();
                break;
            case 5:
                System.out.println(">>Salir");
                System.out.println();
                break;
        }
        System.out.println();
        System.out.println("=========================================");
    }

    public void listUsers() {
        List<User> users = userDAO.select();
        System.out.println("┌────────────┬───────────────┬──────────┐");
        System.out.println("│ ID-Usuario │ NombreUsuario │Contraseña│");
        System.out.println("├────────────┼───────────────┼──────────┤");
        for (int i = 0; i < users.size(); i++) {
            String output = "";
            output += "│" + users.get(i).getIDUser() + "\t     │";
            if (users.get(i).getUserName().length() <= 4) {
                output += users.get(i).getUserName() + "\t        │";
            } else {
                output += users.get(i).getUserName() + "\t     │";
            }
            int espacios = 10 - users.get(i).getPassword().length();
            output += users.get(i).getPassword();
            for (int j = 0; j < espacios; j++) {
                output += " ";
            }
            output += "│";
            System.out.println(output);
            if (i < users.size() - 1) {
                System.out.println("├────────────┼───────────────┼──────────┤");
            }
        }
        System.out.println("└────────────┴───────────────┴──────────┘");
    }

    public void createUser() {
        System.out.print("Ingrese el nombre de usuario: ");
        String userName = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String password = scanner.nextLine();
        User user = new User(userName, password);
        userDAO.insert(user);
        System.out.println("Usuario creado con éxito");
    }

    public void updateUser() {
        System.out.print("Ingrese el ID del usuario a actualizar: ");
        String idUser = scanner.nextLine();
        int idUserInt = 0;
        try {
            idUserInt = Integer.parseInt(idUser);
        } catch (NumberFormatException e) {
            System.out.println("El ID debe ser un número entero");
            return;
        }
        System.out.print("Ingrese el nuevo nombre de usuario: ");
        String userName = scanner.nextLine();
        System.out.print("Ingrese la nueva contraseña: ");
        String password = scanner.nextLine();
        User user = new User(idUserInt, userName, password);
        userDAO.update(user);
        System.out.println("Usuario actualizado con éxito");
    }

    public void deleteUser() {
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        String idUser = scanner.nextLine();
        int idUserInt = 0;
        try {
            idUserInt = Integer.parseInt(idUser);
        } catch (NumberFormatException e) {
            System.out.println("El ID debe ser un número entero");
            return;
        }
        User user = new User(idUserInt);
        userDAO.delete(user);
        System.out.println("Usuario eliminado con éxito");
    }
}
