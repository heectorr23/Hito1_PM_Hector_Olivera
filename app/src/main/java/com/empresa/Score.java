package com.empresa;

// Clase que representa una puntuación
public class Score {
    private int id; // ID de la puntuación
    private String username; // Nombre de usuario
    private int score; // Puntuación

    // Constructor con ID
    public Score(int id, String username, int score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    // Constructor sin ID
    public Score(String username, int score) {
        this.username = username;
        this.score = score;
    }

    // Métodos getter para los atributos
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }
}