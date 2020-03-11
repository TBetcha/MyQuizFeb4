package com.example.myquizfeb4;

public class QuestionBank {

    //declare instance var.
    private int question;
    private boolean trueQuestion;
    private boolean cheated = false;

    //define constructors
    public QuestionBank(int question, boolean trueQuestion) {
        this.question = question;
        this.trueQuestion = trueQuestion;
    }

    //getter method for getting question number
    public int getQuestion() {
        return this.question;
    }

    //setter method for setting question number
    //to change question later use this function
    public void setQuestion(int question) {

        this.question = question;
    }

    //getter method for getting istance var for trueQuestion value
    public boolean checkQuestionAnswer() {
        return this.trueQuestion;
    }

    //setter method for setting instance variable trueQuestion
    public void setTrueQuestion(boolean trueQuestion) {
        this.trueQuestion = trueQuestion;
    }
}
