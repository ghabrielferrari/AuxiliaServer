package br.edu.puccampinas.pi4es2024time4;

import com.google.api.core.ApiFuture;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseService {
    private Firestore db;
    private FirebaseAuth auth;

    public FirebaseService() throws IOException {
        // Inicializando o Firebase com o arquivo de chave
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/seu-arquivo-de-chave.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(com.google.auth.oauth2.GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);

        // Inicializando Firestore e Authentication
        db = FirestoreClient.getFirestore();
        auth = FirebaseAuth.getInstance();
    }

    // Criar ou adicionar um novo usuário no Firestore
    public void criarRegistroUsuario(Usuario usuario) {
        // Referência para a coleção de usuários no Firestore
        DocumentReference docRef = db.collection("usuarios").document(usuario.getId());
        docRef.set(usuario);
    }

    // Obter um usuário do Firestore
    public Usuario obterRegistroUsuario(String usuarioId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("usuarios").document(usuarioId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot snapshot = future.get();
        if (snapshot.exists()) {
            return snapshot.toObject(Usuario.class);
        } else {
            return null; // Usuário não encontrado
        }
    }

    // Atualizar dados de um usuário no Firestore
    public void atualizarRegistroUsuario(String usuarioId, Map<String, Object> atualizacoes) {
        DocumentReference docRef = db.collection("usuarios").document(usuarioId);
        docRef.update(atualizacoes);
    }

    // Deletar um usuário do Firestore
    public void deletarRegistroUsuario(String usuarioId) {
        DocumentReference docRef = db.collection("usuarios").document(usuarioId);
        docRef.delete();
    }

    // Autenticar um usuário no Firebase Auth
    public boolean autenticarUsuario(String email, String senha) {
        try {
            // Simulação de autenticação, pois o FirebaseAuth geralmente é usado com a autenticação via email/senha
            UserRecord userRecord = auth.getUserByEmail(email);
            return userRecord != null && senha.equals("senhaDummy"); // Exemplo simplificado
        } catch (Exception e) {
            System.out.println("Erro na autenticação: " + e.getMessage());
            return false;
        }
    }

    // Verificar se o email já existe no Firebase Authentication
    public boolean verificarEmailExistente(String email) {
        try {
            UserRecord userRecord = auth.getUserByEmail(email);
            return userRecord != null;
        } catch (Exception e) {
            System.out.println("Email não encontrado: " + e.getMessage());
            return false;
        }
    }
}