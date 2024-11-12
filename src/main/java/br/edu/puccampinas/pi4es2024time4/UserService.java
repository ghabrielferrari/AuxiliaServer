package br.edu.puccampinas.pi4es2024time4;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class UserService {

    private FirebaseService firebaseService;

    public UserService() throws IOException {
        firebaseService = new FirebaseService();  // Inicializando a comunicação com Firebase
    }

    public String cadastrarUsuario(Usuario usuario) {
        // Validação de email é feita no servidor agora
        if (emailRegistrado(usuario.getEmail())) {
            return "Email já registrado!";
        }

        firebaseService.criarRegistroUsuario(usuario);
        return "Usuário cadastrado com sucesso!";
    }

    public String loginUsuario(String email, String senha) {
        // Realizando autenticação no Firebase
        if (firebaseService.autenticarUsuario(email, senha)) {
            return "Login bem-sucedido! Token gerado.";
        } else {
            return "Credenciais inválidas!";
        }
    }

    public String atualizarUsuario(Usuario usuario) {
        firebaseService.atualizarRegistroUsuario(usuario.getId(), usuario.toMap());
        return "Usuário atualizado com sucesso!";
    }

    public String deletarUsuario(String usuarioId) {
        firebaseService.deletarRegistroUsuario(usuarioId);
        return "Usuário excluído com sucesso!";
    }

    public Usuario obterDetalhesUsuario(String usuarioId) {
        try {
            return firebaseService.obterRegistroUsuario(usuarioId);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean emailRegistrado(String email) {
        // Verificação do email é feita aqui no servidor
        return firebaseService.verificarEmailExistente(email);
    }
}