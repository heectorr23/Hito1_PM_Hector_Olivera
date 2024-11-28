package com.empresa;

public class Score {
    private int id;
    private String username;
    private int score;

    public Score(int id, String username, int score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    public Score(String username, int score) {
        this.username = username;
        this.score = score;
    }

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