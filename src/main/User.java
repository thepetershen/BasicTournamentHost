package main;

import java.util.ArrayList;

public class User {
    private String userName;
    private String password;
    private ArrayList<Tournament> pastTournaments = new ArrayList<>();

    public User(String name, String password) {
        this.userName = name;
        this.password = password;
    }

    public String getName() {
        return userName;
    }

    public int getNameLength() {
        return userName.length();
    }

    public boolean checkPassword(String passwordInput) {
        if (this.password.equals(passwordInput)) return true;
        return false;
    }

    public void addTournament(Tournament tournament) {
        pastTournaments.add(tournament);
    }

    public ArrayList<Tournament> getTournament() {
        return pastTournaments;
    }

    public boolean playedTournament(Tournament toCheck) {
        return pastTournaments.contains(toCheck);
    }

}
