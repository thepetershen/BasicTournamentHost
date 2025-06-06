package main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class User {

    private final int USER_NAME_MAX_LENGTH = 8;

    private String userName;
    private String password;
    private ArrayList<Tournament> pastTournaments;

    public User(String name, String password) {
        if (name.length() > USER_NAME_MAX_LENGTH ) name = name.substring(0, USER_NAME_MAX_LENGTH);
        this.userName = name;
        this.password = password;
        pastTournaments = new ArrayList<>();
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
    /*
     * This function returns a map of the tournaments the player has played to an arraylist of the matches
     * the user has played in said tournament. this function uses an LinkedHashMap to allow for future 
     * updates for 
     */
    public Map<Tournament, ArrayList<Match>> getAllMatches(){
        Map<Tournament, ArrayList<Match>> tournamentMatches = new LinkedHashMap<>();
        for (Tournament tournament : pastTournaments) {
            ArrayList<Match> matches = tournament.getAllMatchesForUser(this); 
            tournamentMatches.put(tournament, matches);
        }
        return tournamentMatches;
    }
    //same as above but retrieves current matches
    public Map<Tournament, ArrayList<Match>> getCurMatches(){
        Map<Tournament, ArrayList<Match>> tournamentMatches = new LinkedHashMap<>();
        for (Tournament tournament : pastTournaments) {
            ArrayList<Match> matches = tournament.getCurMatchesForUser(this); 
            tournamentMatches.put(tournament, matches);
        }
        return tournamentMatches;
    }

}
