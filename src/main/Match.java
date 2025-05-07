package main;

public class Match {
    private User player1;
    private User player2;
    private Match prevMatch1;
    private Match prevMatch2;
    private User winner;
    private int[] score;

    public Match(User player1, User player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.score = new int[2];
    }
    public User getPlayer1() {
        return player1;
    }

    public User getPlayer2() {
        return player2;
    }
    
    public void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public User getWinner(){
        return winner;
    }

    public void setScore(int[] score) {
        this.score = score;
    }

    public int[] getScore() {
        return score;
    }

    public Match getPrevMatch1() {
        return prevMatch1;
    }
    
    public void setPrevMatch1(Match prevMatch1) {
        this.prevMatch1 = prevMatch1;
    }
    
    public Match getPrevMatch2() {
        return prevMatch2;
    }
    
    public void setPrevMatch2(Match prevMatch2) {
        this.prevMatch2 = prevMatch2;
    }

    public String toString() {
        return player1.getName() + " v. " + player2.getName();
    }
    
}
