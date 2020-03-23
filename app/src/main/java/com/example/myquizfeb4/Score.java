package com.example.myquizfeb4;

public class Score {

    private int score;
    private String number;

    public Score(int score, String number){
        this.score = score;
        this.number = number;
    }

    public int getScore() {
        return this.score;
    }

    public String getNumber() {return this.number;}

    public void setScore(int score) {
        this.score = score;
    }

    public int incrementScore(int score){
        int newScore = score + 1;
        return newScore;
    }
}
