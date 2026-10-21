package co.edu.uptc.interfaces;

public interface IView {
    void showMessage(String message);
    String readString(String prompt);
    int showMenuAndGetOption();
    String recoveryIP();
}