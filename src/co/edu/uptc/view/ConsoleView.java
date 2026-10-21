package co.edu.uptc.view;

import co.edu.uptc.interfaces.IView;
import java.util.Scanner;

public class ConsoleView implements IView {
    private Scanner scanner;

    public ConsoleView() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    @Override
    public int showMenuAndGetOption() {
        System.out.println("\n===== DICCIONARIO TRADUCTOR (UPTC) =====");
        System.out.println("1. Traducir palabra al Español");
        System.out.println("2. Traducir palabra al Francés");
        System.out.println("3. Agregar traducción al diccionario Inglés-Español");
        System.out.println("4. Agregar traducción al diccionario Inglés-Francés");
        System.out.println("5. Ver total de palabras por diccionario");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
        return scanner.nextInt();
    }

    @Override
    public String recoveryIP() {
        System.out.print("Digite la IP del servido: ");
        return scanner.next();
    }
}