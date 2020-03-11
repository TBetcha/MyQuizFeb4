package com.example.myquizfeb4;

public class Score {

    private int score;

    public Score(int score){
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int incrementScore(int score){
        int newScore = score + 1;
        return newScore;
    }
}
