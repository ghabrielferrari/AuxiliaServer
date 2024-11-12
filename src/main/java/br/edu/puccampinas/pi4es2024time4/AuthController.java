package br.edu.puccampinas.pi4es2024time4;

import java.io.IOException;

public class AuthController {

    private UserService userService;

    public AuthController() throws IOException {
        userService = new UserService();
    }

    public String autenticar(String email, String senha) {
        return userService.loginUsuario(email, senha);
    }

    public String autorizar(String token) {
        // Validação do token (simulado)
        return "Token válido!";
    }

    public String logout(String token) {
        // Lógica de logout (simulada)
        return "Logout realizado com sucesso!";
    }
}
