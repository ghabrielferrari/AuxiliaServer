package br.edu.puccampinas.pi4es2024time4;

import java.util.Map;

public class Usuario {
    private String id;
    private String email;
    private String senha;
    private String nome;
    private String cpf;

    // Construtor que inicializa os campos necessários
    public Usuario(String id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    // Getters e setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Método para converter para mapa, útil para atualizações no Firebase
    public Map<String, Object> toMap() {
        return Map.of(
                "email", email,
                "senha", senha,
                "nome", nome,
                "cpf", cpf
        );
    }
}