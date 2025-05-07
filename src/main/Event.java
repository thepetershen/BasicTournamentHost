package main;

import java.util.ArrayList;
import java.util.Map;

public interface Event {

    String getName();

    void addPlayer(User toAdd);

    Map<String, User> getPlayers();

    void createDraw();

    void printDraw();

    void setWinner(Match match, User Winner);

    ArrayList<Match> getAllMatches();

    ArrayList<Match> getCurMatches();
    
} 
