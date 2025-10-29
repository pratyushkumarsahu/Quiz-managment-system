package Model;

import java.util.Date;

import dao.User;

public class QuizResult {
    private int id;
    private User user;
    private Quiz quiz;
    private int score;
    private Date attemptDate;

    public QuizResult() {}

    public QuizResult(int id, User user, Quiz quiz, int score, Date attemptDate) {
        this.id = id;
        this.user = user;
        this.quiz = quiz;
        this.score = score;
        this.attemptDate = attemptDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public Date getAttemptDate() {
        return attemptDate;
    }
    public void setAttemptDate(Date attemptDate) {
        this.attemptDate = attemptDate;
    }
}
