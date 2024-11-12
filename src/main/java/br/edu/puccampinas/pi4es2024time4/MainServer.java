package br.edu.puccampinas.pi4es2024time4;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Map;
import com.google.gson.Gson;

public class MainServer {

    public static void main(String[] args) throws IOException {
        // Criando o servidor HTTP na porta 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Mapeando as rotas para os handlers
        server.createContext("/cadastrarUsuario", new CadastroHandler());
        server.createContext("/loginUsuario", new LoginHandler());
        server.createContext("/atualizarUsuario", new AtualizarHandler());
        server.createContext("/deletarUsuario", new DeletarHandler());
        server.createContext("/obterDetalhesUsuario", new DetalhesHandler());

        // Iniciando o servidor
        server.setExecutor(null); // Usar o executor padrão
        server.start();
        System.out.println("Servidor iniciado na porta 8080");
    }

    // Handler para Cadastro de Usuário
    static class CadastroHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStream inputStream = exchange.getRequestBody();
                String body = new BufferedReader(new InputStreamReader(inputStream)).readLine();

                // Aqui você pode adicionar a lógica para cadastrar um usuário no banco de dados Firebase
                String response = "Cadastro de usuário realizado com sucesso!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Método não permitido
            }
        }
    }

    // Handler para Login de Usuário
    static class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStream inputStream = exchange.getRequestBody();
                String body = new BufferedReader(new InputStreamReader(inputStream)).readLine();

                // Aqui você pode validar o email e senha do usuário
                // Exemplo de validação fictícia
                if (body.contains("email") && body.contains("senha")) {
                    String token = "token_gerado"; // Lógica para gerar um token JWT ou outro tipo de token
                    String response = "Login bem-sucedido! Token: " + token;
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } else {
                    String response = "Credenciais inválidas";
                    exchange.sendResponseHeaders(401, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            } else {
                exchange.sendResponseHeaders(405, -1); // Método não permitido
            }
        }
    }

    // Handler para Atualizar Usuário
    static class AtualizarHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("PUT".equals(exchange.getRequestMethod())) {
                InputStream inputStream = exchange.getRequestBody();
                String body = new BufferedReader(new InputStreamReader(inputStream)).readLine();

                // Aqui você pode adicionar a lógica para atualizar os dados do usuário
                String response = "Usuário atualizado com sucesso!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Método não permitido
            }
        }
    }

    // Handler para Deletar Usuário
    static class DeletarHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("DELETE".equals(exchange.getRequestMethod())) {
                InputStream inputStream = exchange.getRequestBody();
                String body = new BufferedReader(new InputStreamReader(inputStream)).readLine();

                // Aqui você pode adicionar a lógica para deletar o usuário do banco de dados
                String response = "Usuário deletado com sucesso!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Método não permitido
            }
        }
    }

    // Handler para Obter Detalhes do Usuário
    static class DetalhesHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                // A lógica para obter os detalhes do usuário pode ser baseada no ID passado como parâmetro
                String response = "Detalhes do usuário obtidos com sucesso!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Método não permitido
            }
        }
    }
}