package co.edu.uptc.controller;

import co.edu.uptc.interfaces.IView;
import co.edu.uptc.view.ConsoleView;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerClient {

    private static final int PORT = 1234;
    private IView view;

    public ServerClient() {
        this.view = new ConsoleView();
    }

    public void start() {
        String ip = view.recoveryIP();
        try (
                Socket socket = new Socket(ip, PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
            view.showMessage("\nServidor: " + input.readUTF());
            menuPrincipal(input, output);
        } catch (IOException e) {
            view.showMessage("Error de conexión con el servidor: " + e.getMessage());
        }
    }

    private void menuPrincipal(DataInputStream input, DataOutputStream output) throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            int option = view.showMenuAndGetOption();
            switch (option) {
                case 1:
                    translateToSpanish(input, output);
                    break;
                case 2:
                    translateToFrench(input, output);
                    break;
                case 3:
                    addSpanishWord(input, output);
                    break;
                case 4:
                    addFrenchWord(input, output);
                    break;
                case 5:
                    showCounts(input, output);
                    break;
                case 6:
                    output.writeInt(6);
                    isRunning = false;
                    view.showMessage("Cerrando cliente...");
                    break;
                default:
                    view.showMessage("Opción no válida.");
                    break;
            }
        }
    }

    private void translateToSpanish(DataInputStream input, DataOutputStream output) throws IOException {
        output.writeInt(1);
        String english = view.readString("Ingrese la palabra en inglés: ");
        output.writeUTF(english);

        if (input.readBoolean()) {
            view.showMessage("Traducción al español: " + input.readUTF());
        } else {
            view.showMessage("La palabra no se encuentra en el diccionario Inglés-Español.");
        }
    }

    private void translateToFrench(DataInputStream input, DataOutputStream output) throws IOException {
        output.writeInt(2);
        String english = view.readString("Ingrese la palabra en inglés: ");
        output.writeUTF(english);

        if (input.readBoolean()) {
            view.showMessage("Traducción al francés: " + input.readUTF());
        } else {
            view.showMessage("La palabra no se encuentra en el diccionario Inglés-Francés.");
        }
    }

    private void addSpanishWord(DataInputStream input, DataOutputStream output) throws IOException {
        output.writeInt(3);
        String english = view.readString("Ingrese la palabra en inglés: ");
        String translation = view.readString("Ingrese la traducción en español: ");
        output.writeUTF(english);
        output.writeUTF(translation);

        if (input.readBoolean()) {
            view.showMessage("Palabra agregada exitosamente.");
        } else {
            view.showMessage("La palabra en inglés ya existe en este diccionario.");
        }
    }

    private void addFrenchWord(DataInputStream input, DataOutputStream output) throws IOException {
        output.writeInt(4);
        String english = view.readString("Ingrese la palabra en inglés: ");
        String translation = view.readString("Ingrese la traducción en francés: ");
        output.writeUTF(english);
        output.writeUTF(translation);

        if (input.readBoolean()) {
            view.showMessage("Palabra agregada exitosamente.");
        } else {
            view.showMessage("La palabra en inglés ya existe en este diccionario.");
        }
    }

    private void showCounts(DataInputStream input, DataOutputStream output) throws IOException {
        output.writeInt(5);
        output.flush();

        int spanishCount = input.readInt();
        int frenchCount = input.readInt();

        view.showMessage("\n--- Total de Palabras ---");
        view.showMessage("Diccionario Inglés-Español: " + spanishCount + " palabras");
        view.showMessage("Diccionario Inglés-Francés: " + frenchCount + " palabras");
    }
}