import co.edu.uptc.controller.ServerClient;

public class App {
    public static void main(String[] args) throws Exception {
        ServerClient client = new ServerClient();
        client.start();
    }
}