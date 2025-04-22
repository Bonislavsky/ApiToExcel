package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import org.example.jsonModels.*;

public class MagicDataCreator {

    private final HttpClient client;
    private final Gson gson;

    public MagicDataCreator() {
        client = HttpClient.newHttpClient();
        gson = new Gson();
    }

    public Book[] GetBooks(String token){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.abibliadigital.com.br/api/books"))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        Book[] books = new Book[0];
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            books = gson.fromJson(response.body(), Book[].class);
        }
        catch(Exception e){System.out.println(e.getMessage());}

        return books;
    }

    public String GetToken(String email, String password){   //return 500
        String jsonBody = String.format("""
                {
                    "email": "%s",
                    "password": "%s"
                }
                """,email,password );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.abibliadigital.com.br/api/users/token"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        User user = null;
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            user = gson.fromJson(response.body(), User.class);
        }
        catch(Exception e){System.out.println(e.getMessage());}

        return user.GetToken();
    }

    public void CreateUser(String email, String password){
        String jsonBody = String.format("""
                {
                    "name": "musorkaman",
                    "email": "%s",
                    "password": "%s",
                    "notifications": false
                }
                """,email,password );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.abibliadigital.com.br/api/users"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
        }
        catch(Exception e){System.out.println(e.getMessage());}
    }
}
